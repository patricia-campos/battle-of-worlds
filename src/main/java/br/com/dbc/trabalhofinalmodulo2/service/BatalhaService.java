package br.com.dbc.trabalhofinalmodulo2.service;

import br.com.dbc.trabalhofinalmodulo2.exceptions.BancoDeDadosException;
import br.com.dbc.trabalhofinalmodulo2.exceptions.BossNaoEncontradoException;
import br.com.dbc.trabalhofinalmodulo2.exceptions.VidaMenorQueZero;
import br.com.dbc.trabalhofinalmodulo2.mapper.BatalhaMapper;
import br.com.dbc.trabalhofinalmodulo2.model.dto.BatalhaCreateDTO;
import br.com.dbc.trabalhofinalmodulo2.model.dto.BatalhaDTO;
import br.com.dbc.trabalhofinalmodulo2.model.entities.*;
import br.com.dbc.trabalhofinalmodulo2.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BatalhaService {

    @Autowired
    BatalhaRepository batalhaRepository;

    @Autowired
    BatalhaMapper batalhaMapper;

    @Autowired
    BossRepository bossRepository;

    @Autowired
    CenarioRepository cenarioRepository;

    @Autowired
    JogadorRepository jogadorRepository;

    @Autowired
    PersonagemRepository personagemRepository;

    @Autowired
    ClassePersonagemRepository classePersonagemRepository;


    public BatalhaDTO adicionar(BatalhaCreateDTO batalha) throws BancoDeDadosException {
        Batalha batalha1 = batalhaMapper.fromCreateDTO(batalha);
        return batalhaMapper.toBatalhaDTO(batalhaRepository.adicionar(batalha1));
    }

    public List<BatalhaCreateDTO> listar() throws BancoDeDadosException {
        List<Batalha> batalhaList = batalhaRepository.listar();
        return batalhaList.stream()
                .map(a -> batalhaMapper.toCreateDTO(a))
                .collect(Collectors.toList());
    }

    //No momento do projeto não está implementado
    public void remover(Batalha batalha) throws BancoDeDadosException {
        batalhaRepository.remover(batalha.getIdBatalha());
    }

    //No momento do projeto não está implementado
    public void editar(Integer id, Batalha batalha) throws BancoDeDadosException {
        batalhaRepository.editar(id, batalha);
    }

    public String atacar(int idBatalha) throws BossNaoEncontradoException, BancoDeDadosException, VidaMenorQueZero {


        Batalha batalha = batalhaRepository.buscarBatalha(idBatalha);
        Boss bossAtacado = bossRepository.buscarBoss(batalha.getIdBoss());
        Personagem personagem = personagemRepository.retornaPersonagemPorJogador(batalha.getIdJogador());
        ClassePersonagem classePersonagem = classePersonagemRepository.listarClassePorPersonagemID(personagem.getId());
        if (bossAtacado.getVida() <= 0) {
            throw new VidaMenorQueZero("Vida do boss menor que 0");
        }else if (classePersonagem.getVidaClasse() == 0){
            throw new VidaMenorQueZero("Vida do jogador menor que 0");
        }
        Double defesaBoss = bossAtacado.getDefesa() * 0.4;
        Double ataqueDoJogador = classePersonagem.getAtaqueClasse();
        Double danoEfetuado = defesaBoss - ataqueDoJogador;
        Double vidaNova = bossAtacado.getVida() - danoEfetuado;
        bossAtacado.setVida(vidaNova);
        bossRepository.editar(batalha.getIdBoss(), bossAtacado);

        return "Seu dano foi de: " + danoEfetuado + "\nA vida do boss é " + vidaNova;
    }

    public String defender(int idBatalha) throws BancoDeDadosException, BossNaoEncontradoException, VidaMenorQueZero {
        Batalha batalha = batalhaRepository.buscarBatalha(idBatalha);
        Boss bossAtacado = bossRepository.buscarBoss(batalha.getIdBoss());
        Double ataqueBoss = bossAtacado.getAtaque();
        Personagem personagem = personagemRepository.retornaPersonagemPorJogador(batalha.getIdJogador());
        ClassePersonagem classePersonagem = classePersonagemRepository.listarClassePorPersonagemID(personagem.getId());
        Double defesaJogador = classePersonagem.getDefesaClasse() * 0.5;
        Double danoEfetuado = defesaJogador - ataqueBoss;
        Double vidaNova = classePersonagem.getVidaClasse() - danoEfetuado;

        if (bossAtacado.getVida() <= 0) {
            throw new VidaMenorQueZero("Vida do boss menor que 0");
        }else if (classePersonagem.getVidaClasse() == 0){
            throw new VidaMenorQueZero("Vida do jogador menor que 0");
        }

        classePersonagem.setVidaClasse(vidaNova);
        personagemRepository.editar(personagem.getId(), personagem);

        return "Defesa bem sucedida você levou " + danoEfetuado + " de dano do boss" + "\n Sua vida e de: " + classePersonagem.getVidaClasse();
    }

    public String fugir(int idBatalha) throws BancoDeDadosException {
        Batalha batalha = batalhaRepository.buscarBatalha(idBatalha);
        batalha.setStatus("Derrota");
        batalhaRepository.adicionar(batalha);

        return "Você fugiu com sucesso o boss era de mais para você :( ";
    }

}
