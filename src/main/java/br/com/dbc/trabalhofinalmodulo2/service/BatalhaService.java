package br.com.dbc.trabalhofinalmodulo2.service;

import br.com.dbc.trabalhofinalmodulo2.exceptions.NaoEncontradoException;
import br.com.dbc.trabalhofinalmodulo2.mapper.BossMapper;
import br.com.dbc.trabalhofinalmodulo2.model.entities.Batalha;
import br.com.dbc.trabalhofinalmodulo2.model.entities.Boss;
import br.com.dbc.trabalhofinalmodulo2.model.entities.ClassePersonagem;
import br.com.dbc.trabalhofinalmodulo2.model.entities.Personagem;
import br.com.dbc.trabalhofinalmodulo2.exceptions.BancoDeDadosException;
import br.com.dbc.trabalhofinalmodulo2.exceptions.VidaMenorQueZero;
import br.com.dbc.trabalhofinalmodulo2.mapper.BatalhaMapper;
import br.com.dbc.trabalhofinalmodulo2.model.dto.BatalhaCreateDTO;
import br.com.dbc.trabalhofinalmodulo2.model.dto.BatalhaDTO;
import br.com.dbc.trabalhofinalmodulo2.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BatalhaService {

    @Autowired
    BatalhaRepository batalhaRepository;

    @Autowired
    BatalhaMapper batalhaMapper;

    @Autowired
    BossMapper bossMapper;

    @Autowired
    BossService bossService;

    @Autowired
    CenarioService cenarioService;

    @Autowired
    JogadorService jogadorService;

    @Autowired
    PersonagemRepository personagemRepository;

    @Autowired
    ClassePersonagemRepository classePersonagemRepository;

    public BatalhaDTO adicionar(BatalhaCreateDTO batalha) throws BancoDeDadosException, Exception {
        jogadorService.listarPorId(batalha.getIdJogador());
        bossService.buscarBoss(batalha.getIdBoss());
        cenarioService.buscarCenario(batalha.getIdCenario());
        Batalha batalha1 = batalhaMapper.fromCreateDTO(batalha);
        return batalhaMapper.toBatalhaDTO(batalhaRepository.adicionar(batalha1));
    }

    public List<BatalhaCreateDTO> listar() throws BancoDeDadosException, SQLException {
        List<Batalha> batalhaList = batalhaRepository.listar();
        return batalhaList.stream()
                .map(a -> batalhaMapper.toCreateDTO(a))
                .collect(Collectors.toList());
    }

    //No momento do projeto não está implementado
    public void remover(Integer id) throws BancoDeDadosException, SQLException, NaoEncontradoException {
        findById(id);
        batalhaRepository.remover(id);
    }

    //No momento do projeto não está implementado
    public void editar(Integer id, Batalha batalha) throws BancoDeDadosException, SQLException {
        batalhaRepository.editar(id, batalha);
    }

    public String atacar(int idBatalha) throws Exception, BancoDeDadosException {

        Batalha batalha = batalhaRepository.buscarBatalha(idBatalha);
        Boss bossAtacado = bossService.buscarBoss(batalha.getIdBoss());
        Personagem personagem = personagemRepository.retornaPersonagemPorJogador(batalha.getIdJogador());
        if(personagem == null){
            throw new NaoEncontradoException("Jogador não possui personagem");
        }
        ClassePersonagem classePersonagem = classePersonagemRepository.listarClassePorPersonagemID(personagem.getId());
        if (bossAtacado.getVida() <= 0) {
            batalha.setStatus("Vitoria");
            batalhaRepository.editar(batalha.getIdBatalha(),batalha);
            throw new VidaMenorQueZero("Vida do boss menor que 0");
        }else if (classePersonagem.getVidaClasse() == 0){
            batalha.setStatus("Derrota");
            batalhaRepository.editar(batalha.getIdBatalha(),batalha);
            throw new VidaMenorQueZero("Vida do jogador menor que 0");
        }
        Double defesaBoss = bossAtacado.getDefesa() * 0.4;
        Double ataqueDoJogador = classePersonagem.getAtaqueClasse();
        Double danoEfetuado = ataqueDoJogador - defesaBoss;
        Double vidaNova = bossAtacado.getVida() - danoEfetuado;
        bossAtacado.setVida(vidaNova);
        batalha.setRoundBatalha(batalha.getRoundBatalha() + 1);
        batalhaRepository.editar(batalha.getIdBatalha(),batalha);
        bossService.editar(bossMapper.toCreateDTO(bossAtacado), batalha.getIdBoss());

        return "Seu dano foi de: " + danoEfetuado + "\nA vida do boss é " + vidaNova;
    }

    public String defender(int idBatalha) throws BancoDeDadosException, Exception {
        Batalha batalha = batalhaRepository.buscarBatalha(idBatalha);
        Boss bossAtacado = bossService.buscarBoss(batalha.getIdBoss());
        Double ataqueBoss = bossAtacado.getAtaque();
        Personagem personagem = personagemRepository.retornaPersonagemPorJogador(batalha.getIdJogador());
        ClassePersonagem classePersonagem = classePersonagemRepository.listarClassePorPersonagemID(personagem.getId());
        Double defesaJogador = classePersonagem.getDefesaClasse() * 0.5;
        Double danoEfetuado =  ataqueBoss - defesaJogador;
        Double vidaNova = classePersonagem.getVidaClasse() - danoEfetuado;


        if (bossAtacado.getVida() <= 0) {
            batalha.setStatus("Vitoria");
            batalhaRepository.editar(batalha.getIdBatalha(),batalha);
            throw new VidaMenorQueZero("Vida do boss menor que 0");
        }else if (classePersonagem.getVidaClasse() == 0){
            batalha.setStatus("Derrota");
            batalhaRepository.editar(batalha.getIdBatalha(),batalha);
            throw new VidaMenorQueZero("Vida do jogador menor que 0");
        }
        classePersonagem.setVidaClasse(vidaNova);
        batalha.setRoundBatalha(batalha.getRoundBatalha() + 1);
        batalhaRepository.editar(batalha.getIdBatalha(),batalha);
        classePersonagemRepository.editar(classePersonagem.getIdPersonagem(), classePersonagem);

        return "Defesa bem sucedida você levou " + danoEfetuado + " de dano do boss" + "\n Sua vida e de: " + classePersonagem.getVidaClasse();
    }

    public String fugir(int idBatalha) throws BancoDeDadosException, SQLException {
        Batalha batalha = batalhaRepository.buscarBatalha(idBatalha);
        batalha.setStatus("Derrota");
        batalhaRepository.editar(batalha.getIdBatalha(),batalha);

        return "Você fugiu com sucesso o boss era de mais para você :( ";
    }

    private Batalha findById(Integer id) throws BancoDeDadosException, SQLException, NaoEncontradoException {
        Batalha batalha = batalhaRepository.buscarBatalha(id);
        if (batalha == null) {
            throw new NaoEncontradoException("Batalha não encontrada");
        }
        return batalha;
    }
}
