package br.com.dbc.trabalhofinalmodulo2.service;

import br.com.dbc.trabalhofinalmodulo2.exceptions.BancoDeDadosException;
import br.com.dbc.trabalhofinalmodulo2.exceptions.NaoEncontradoException;
import br.com.dbc.trabalhofinalmodulo2.mapper.JogadorMapper;
import br.com.dbc.trabalhofinalmodulo2.model.dto.JogadorCreateDTO;
import br.com.dbc.trabalhofinalmodulo2.model.dto.JogadorDTO;
import br.com.dbc.trabalhofinalmodulo2.model.entities.Jogador;
import br.com.dbc.trabalhofinalmodulo2.repository.JogadorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class JogadorService {

    @Autowired
    private JogadorRepository jogadorRepository;

    @Autowired
    private JogadorMapper jogadorMapper;

    @Autowired
    private EmailService emailService;

    public JogadorDTO adicionar(JogadorCreateDTO jogador) throws BancoDeDadosException, SQLException, NaoEncontradoException {
        log.info("Jogador criado");
         if (jogadorRepository.listarPorNome(jogador.getNomeJogador()).isPresent()) {
             throw new NaoEncontradoException("Jogador já existe");
         }
        Jogador jogadorEntity = jogadorMapper.fromCreateDTO(jogador);
        JogadorDTO jogadorDTO = jogadorMapper.toDTO(jogadorRepository.adicionar(jogadorEntity));

        //Chama método de envio de e-mail
        emailService.sendEmailJogadorCadastrado(jogadorDTO, jogadorEntity);
        log.warn("Enviando E-mail.. " + jogadorDTO.getEmail()+ "!");

        return jogadorDTO;
    }

    public List<JogadorDTO> listarTodos() throws BancoDeDadosException, SQLException {
        return jogadorRepository.listar().stream().map(jogadorMapper::toDTO).toList();
    }

    public void remover(JogadorDTO jogador) throws BancoDeDadosException, SQLException {
        log.info("Jogador Deletado");
        Jogador jogadorRecuperado = jogadorRepository.listarPorId(jogador.getId());
        JogadorDTO jogadorDTO = jogadorMapper.toDTO(jogadorRecuperado);

        //Chama método de envio de e-mail
        emailService.sendEmailJogadorExcluido(jogadorDTO);
        log.warn("Enviando E-mail.. " + jogadorDTO.getEmail()+ "!");

        jogadorRepository.remover(jogadorDTO.getId());
    }

    public JogadorDTO editar(JogadorCreateDTO jogador, Integer id) throws BancoDeDadosException, SQLException, NaoEncontradoException {
        log.info("Jogador Editado");
        Jogador jogadorRecuperado = jogadorMapper.fromCreateDTO(listarPorId(id));
        jogadorRecuperado.setNomeJogador(jogador.getNomeJogador());
        jogadorRecuperado.setSenha(jogador.getSenha());
        jogadorRecuperado.setEmail(jogador.getEmail());
        jogadorRepository.editar(id, jogadorRecuperado);

        //Chama método de envio de e-mail
        emailService.sendEmailJogadorEditado(jogador);
        log.warn("Enviando E-mail.. " + jogadorRecuperado.getEmail()+ "!");

        return jogadorMapper.toDTO(jogadorRecuperado);
    }

    public Optional<Jogador> retornaJogador(String nome) throws BancoDeDadosException, SQLException {
        return jogadorRepository.listarPorNome(nome);
    }

    public JogadorDTO listarPorId(Integer id) throws BancoDeDadosException, SQLException, NaoEncontradoException {
        Jogador jogadorRecuperado = jogadorRepository.listarPorId(id);
        if (jogadorRecuperado == null) {
            throw new NaoEncontradoException("Jogador não encontrado");
        }
        jogadorMapper.toDTO(jogadorRecuperado);
        JogadorDTO jogadorDTO = jogadorMapper.toDTO(jogadorRecuperado);
        return jogadorDTO;
    }

}
