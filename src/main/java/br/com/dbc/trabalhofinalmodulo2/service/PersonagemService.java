package br.com.dbc.trabalhofinalmodulo2.service;

import br.com.dbc.trabalhofinalmodulo2.exceptions.BancoDeDadosException;
import br.com.dbc.trabalhofinalmodulo2.mapper.PersonagemMapper;
import br.com.dbc.trabalhofinalmodulo2.model.dto.*;
import br.com.dbc.trabalhofinalmodulo2.model.entities.Personagem;
import br.com.dbc.trabalhofinalmodulo2.repository.PersonagemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PersonagemService {

    @Autowired
    private PersonagemRepository personagemRepository;

    @Autowired
    private PersonagemMapper personagemMapper;


    public PersonagemDTO adicionar(PersonagemCreateDTO personagem, Integer idJogador) throws BancoDeDadosException {
        log.info("Personagem criado");
        if (personagemRepository.listarPorNome(personagem.getNomePersonagem()).isPresent()) {
            throw new BancoDeDadosException("Personagem já existe");
        }

        Personagem personagemEntity = personagemMapper.fromCreateDTO(personagem);
        PersonagemDTO personagemDTO = personagemMapper.toDTO(personagemRepository.adicionar(personagemEntity, idJogador));
        return personagemDTO;
    }

    public List<PersonagemDTO> listarTodos() throws BancoDeDadosException {
        return personagemRepository.listar().stream().map(personagemMapper::toDTO).toList();
    }

    public PersonagemDTO editar(PersonagemPutDTO personagem, Integer idPersonagem) throws BancoDeDadosException {
        if (personagemRepository.listarPorNome(personagem.getNomePersonagem()).isPresent()) {
            throw new BancoDeDadosException("Personagem já existe");
        }
        Personagem personagemDoBanco = personagemRepository.listarPorId(idPersonagem);
        personagemDoBanco.setNomePersonagem(personagem.getNomePersonagem());
        personagem.setNomePersonagem(personagem.getNomePersonagem());
        PersonagemDTO personagemDTO = personagemMapper.toDTO(personagemRepository.editar(idPersonagem, personagemDoBanco));
        return personagemDTO;
    }


    public void listar() throws BancoDeDadosException {
        for (Personagem personagem : personagemRepository.listar()) {
            System.out.println(personagem);
        }
    }

    public void remover(PersonagemDTO personagem) throws BancoDeDadosException {
        Personagem personagemRecuperado = personagemRepository.listarPorId(personagem.getId());
        PersonagemDTO personagemDTO = personagemMapper.toDTO(personagemRecuperado);
        personagemRepository.remover(personagemDTO.getId());
    }

    public PersonagemDTO listarPorId(Integer id) throws BancoDeDadosException {
        Personagem personagemRecuperado = personagemRepository.listarPorId(id);
        PersonagemDTO personagemDTO = personagemMapper.toDTO(personagemRecuperado);
        return personagemDTO;
    }

    public void listarPersonagemsPorJogador(int idJogador) throws BancoDeDadosException {
        List<Personagem> listaPersonagem = personagemRepository.listar().stream().filter(a -> Objects.equals(a.getIdJogador(), idJogador)).collect(Collectors.toList());
        for (Personagem personagem : listaPersonagem) {
            System.out.println(personagem);
        }
    }
}
