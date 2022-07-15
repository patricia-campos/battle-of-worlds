package br.com.dbc.trabalhofinalmodulo2.service;

import br.com.dbc.trabalhofinalmodulo2.exceptions.BancoDeDadosException;
import br.com.dbc.trabalhofinalmodulo2.mapper.JogadorMapper;
import br.com.dbc.trabalhofinalmodulo2.model.dto.JogadorCreateDTO;
import br.com.dbc.trabalhofinalmodulo2.model.dto.JogadorDTO;
import br.com.dbc.trabalhofinalmodulo2.model.entities.Jogador;
import br.com.dbc.trabalhofinalmodulo2.repository.JogadorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class JogadorService {

    @Autowired
    private JogadorRepository jogadorRepository;

    @Autowired
    private JogadorMapper jogadorMapper;

    public JogadorDTO adicionar(JogadorCreateDTO jogador) throws BancoDeDadosException {
        log.info("Jogador criado");
         if (jogadorRepository.listarPorNome(jogador.getNomeJogador()).isPresent()) {
             throw new BancoDeDadosException("Jogador já existe");
         }
        Jogador jogadorEntity = jogadorMapper.fromCreateDTO(jogador);
        JogadorDTO jogadorDTO = jogadorMapper.toDTO(jogadorRepository.adicionar(jogadorEntity));
        return jogadorDTO;
    }

    public List<JogadorDTO> listarTodos() throws BancoDeDadosException {
        return jogadorRepository.listar().stream().map(jogadorMapper::toDTO).toList();
    }

    public void remover(JogadorDTO jogador) throws BancoDeDadosException {
        log.info("Jogador Deletado");
        Jogador jogadorRecuperado = jogadorRepository.listarPorId(jogador.getId());
        JogadorDTO jogadorDTO = jogadorMapper.toDTO(jogadorRecuperado);
        jogadorRepository.remover(jogadorDTO.getId());
    }

    public JogadorDTO editar(JogadorCreateDTO jogador, Integer id) throws BancoDeDadosException {
        log.info("Jogador Editado");
        Jogador jogadorRecuperado = jogadorRepository.listarPorId(id);
        jogadorRecuperado.setNomeJogador(jogador.getNomeJogador());
        jogadorRecuperado.setSenha(jogador.getSenha());
        jogadorRecuperado.setEmail(jogador.getEmail());
        jogadorRepository.editar(id, jogadorRecuperado);
        return jogadorMapper.toDTO(jogadorRecuperado);
    }

    public Optional<Jogador> retornaJogador(String nome) throws BancoDeDadosException {
        return jogadorRepository.listarPorNome(nome);
    }

//    public Jogador findById(Integer idPessoa) throws BancoDeDadosException {
//        Jogador pessoaRecuperada = jogadorRepository.listar().stream()
//                .filter(pessoa -> pessoa.getId().equals(idPessoa))
//                .findFirst()
//                .orElseThrow(() -> new BancoDeDadosException("Pessoa não encontrada"));
//        JogadorDTO jogadorDTO = jogadorMapper.toDTO(pessoaRecuperada);
//        return pessoaRecuperada;
//    }

    public JogadorDTO listarPorId(Integer id) throws BancoDeDadosException {
        Jogador jogadorRecuperado = jogadorRepository.listarPorId(id);
        JogadorDTO jogadorDTO = jogadorMapper.toDTO(jogadorRecuperado);
        return jogadorDTO;
    }
}
