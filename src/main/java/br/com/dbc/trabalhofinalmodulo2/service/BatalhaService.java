package br.com.dbc.trabalhofinalmodulo2.service;

import br.com.dbc.trabalhofinalmodulo2.entities.*;
import br.com.dbc.trabalhofinalmodulo2.exceptions.NaoEncontradoException;
import br.com.dbc.trabalhofinalmodulo2.mapper.BossMapper;
import br.com.dbc.trabalhofinalmodulo2.exceptions.BancoDeDadosException;
import br.com.dbc.trabalhofinalmodulo2.exceptions.VidaMenorQueZero;
import br.com.dbc.trabalhofinalmodulo2.mapper.BatalhaMapper;
import br.com.dbc.trabalhofinalmodulo2.dto.BatalhaCreateDTO;
import br.com.dbc.trabalhofinalmodulo2.dto.BatalhaDTO;
import br.com.dbc.trabalhofinalmodulo2.mapper.JogadorMapper;
import br.com.dbc.trabalhofinalmodulo2.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BatalhaService {

    @Autowired
    private BatalhaRepository batalhaRepository;

    @Autowired
    private BatalhaMapper batalhaMapper;

    @Autowired
    private BossMapper bossMapper;

    @Autowired
    private BossService bossService;

    @Autowired
    private CenarioService cenarioService;

    @Autowired
    private JogadorService jogadorService;

    @Autowired
    private PersonagemRepository personagemRepository;

    @Autowired
    private JogadorMapper jogadorMapper;

    @Autowired
    private ClassePersonagemRepository classePersonagemRepository;

    public BatalhaDTO adicionar(BatalhaCreateDTO batalha) throws BancoDeDadosException, Exception {
        Jogador jogador = jogadorMapper.fromCreateDTO(jogadorService.listarPorId(batalha.getIdJogador()));

        if(personagemRepository.retornaPersonagemPorJogador(jogador.getId()) == null){
            throw new NaoEncontradoException("Jogador não possui personagem");
        }

        bossService.buscarBoss(batalha.getIdBoss());
        cenarioService.verificarCenario(batalha.getIdCenario());
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
        verificaExistente(id);
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

        verificaVidaBossJogador(bossAtacado,classePersonagem,batalha);

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

        verificaVidaBossJogador(bossAtacado,classePersonagem,batalha);

        classePersonagem.setVidaClasse(vidaNova);
        batalha.setRoundBatalha(batalha.getRoundBatalha() + 1);
        batalhaRepository.editar(batalha.getIdBatalha(),batalha);
        classePersonagemRepository.editar(classePersonagem.getIdPersonagem(), classePersonagem);

        return "Defesa bem sucedida você levou " + danoEfetuado + " de dano do boss" + "\n Sua vida e de: " + classePersonagem.getVidaClasse();
    }

    public void verificaVidaBossJogador(Boss bossAtacado, ClassePersonagem classePersonagem, Batalha batalha) throws VidaMenorQueZero, BancoDeDadosException, SQLException {
        if (bossAtacado.getVida() <= 0) {
            batalha.setStatus("Vitoria");
            batalhaRepository.editar(batalha.getIdBatalha(),batalha);
            throw new VidaMenorQueZero("Vida do boss menor que 0");
        }else if (classePersonagem.getVidaClasse() <= 0){
            batalha.setStatus("Derrota");
            batalhaRepository.editar(batalha.getIdBatalha(),batalha);
            throw new VidaMenorQueZero("Vida do jogador menor que 0");
        }
    }

    public String fugir(int idBatalha) throws BancoDeDadosException, SQLException {
        Batalha batalha = batalhaRepository.buscarBatalha(idBatalha);
        batalha.setStatus("Derrota");
        batalhaRepository.editar(batalha.getIdBatalha(),batalha);

        return "Você fugiu com sucesso o boss era de mais para você :( ";
    }

    private void verificaExistente(Integer id) throws BancoDeDadosException, SQLException, NaoEncontradoException {
        Batalha batalha = batalhaRepository.buscarBatalha(id);
        if (batalha == null) {
            throw new NaoEncontradoException("Batalha não encontrada");
        }
    }
}
