package br.com.dbc.trabalhofinalmodulo2.service;

import br.com.dbc.trabalhofinalmodulo2.exceptions.BancoDeDadosException;
import br.com.dbc.trabalhofinalmodulo2.exceptions.BossNaoEncontradoException;
import br.com.dbc.trabalhofinalmodulo2.mapper.BatalhaMapper;
import br.com.dbc.trabalhofinalmodulo2.model.dto.BatalhaCreateDTO;
import br.com.dbc.trabalhofinalmodulo2.model.dto.BatalhaDTO;
import br.com.dbc.trabalhofinalmodulo2.model.entities.Batalha;
import br.com.dbc.trabalhofinalmodulo2.model.entities.Boss;
import br.com.dbc.trabalhofinalmodulo2.model.entities.Jogador;
import br.com.dbc.trabalhofinalmodulo2.repository.BatalhaRepository;
import br.com.dbc.trabalhofinalmodulo2.repository.BossRepository;
import br.com.dbc.trabalhofinalmodulo2.repository.CenarioRepository;
import br.com.dbc.trabalhofinalmodulo2.repository.JogadorRepository;
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

    public String atacar(int idBatalha) throws BossNaoEncontradoException, BancoDeDadosException {
        Batalha batalha = batalhaRepository.buscarBatalha(idBatalha);
        Boss bossAtacado = bossRepository.buscarBoss(batalha.getIdBoss());
        Double defesaBoss = bossAtacado.getDefesa() * 0.4;
        Jogador jogadorAtacante = jogadorRepository.listarPorId(batalha.getIdJogador());

        bossAtacado.setVida(bossAtacado.getVida() + defesaBoss - 10);
        bossRepository.editar(batalha.getIdBoss(), bossAtacado);

    return "Ataque bem sucedido você causou " + 10 + " de dano no boss";
    }

    public String defender(int idBatalha) throws BancoDeDadosException, BossNaoEncontradoException {
        Batalha batalha = batalhaRepository.buscarBatalha(idBatalha);
        Boss bossAtacado = bossRepository.buscarBoss(batalha.getIdBoss());
        Double ataqueBoss = bossAtacado.getAtaque();
        Jogador jogadorAtacante = jogadorRepository.listarPorId(batalha.getIdJogador());

        return "Defesa bem sucedida você levou " + 10 + " de dano do boss";
    }

    public String fugir(int idBatalha) throws BancoDeDadosException, BossNaoEncontradoException {
        Batalha batalha = batalhaRepository.buscarBatalha(idBatalha);
        batalhaRepository.adicionar(batalha);

        return "Você fugiu com sucesso o boss era de mais para você :( ";
    }

}
