package br.com.dbc.trabalhofinalmodulo2.service;

import br.com.dbc.trabalhofinalmodulo2.exceptions.BancoDeDadosException;
import br.com.dbc.trabalhofinalmodulo2.exceptions.NaoEncontradoException;
import br.com.dbc.trabalhofinalmodulo2.mapper.ClassePersonagemMapper;
import br.com.dbc.trabalhofinalmodulo2.mapper.PersonagemMapper;
import br.com.dbc.trabalhofinalmodulo2.dto.PersonagemCreateDTO;
import br.com.dbc.trabalhofinalmodulo2.dto.PersonagemDTO;
import br.com.dbc.trabalhofinalmodulo2.dto.PersonagemPutDTO;
import br.com.dbc.trabalhofinalmodulo2.entities.Personagem;
import br.com.dbc.trabalhofinalmodulo2.repository.PersonagemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
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

    @Autowired
    private ClassePersonagemService classePersonagemService;

    @Autowired
    ClassePersonagemMapper classePersonagemMapper;
    public PersonagemDTO adicionar(PersonagemCreateDTO personagem, Integer idJogador) throws BancoDeDadosException, SQLException {
        log.info("Personagem criado");
        if (personagemRepository.listarPorNome(personagem.getNomePersonagem()).isPresent()) {
            throw new BancoDeDadosException("Personagem já existe");
        }

        Personagem personagemEntity = personagemMapper.fromCreateDTO(personagem);
        PersonagemDTO personagemDTO = personagemMapper.toDTO(personagemRepository.adicionar(personagemEntity, idJogador));
        return personagemDTO;
    }

    public List<PersonagemDTO> listarTodos() throws BancoDeDadosException, SQLException {
        List<PersonagemDTO> listaDto = personagemRepository.listar().stream().map(personagemMapper::toDTO).toList();
        listaDto.forEach(p -> {
             try {
                 p.setClassePersonagem(classePersonagemService.retornaClassePorPersonagem(p.getId()));
             } catch (BancoDeDadosException | SQLException e) {
                 e.printStackTrace();
             }
        });
         return listaDto;
    }

    public PersonagemDTO editar(PersonagemPutDTO personagem, Integer idPersonagem) throws BancoDeDadosException, SQLException, NaoEncontradoException {
        Personagem personagemDoBanco = personagemMapper.fromCreateDTO(listarPorId(idPersonagem));
        if (personagemRepository.listarPorNome(personagem.getNomePersonagem()).isPresent()) {
            throw new BancoDeDadosException("Personagem já existe");
        }
        personagemDoBanco.setNomePersonagem(personagem.getNomePersonagem());
        personagem.setNomePersonagem(personagem.getNomePersonagem());
        PersonagemDTO personagemDTO = personagemMapper.toDTO(personagemRepository.editar(idPersonagem, personagemDoBanco));
        personagemDTO.setClassePersonagem(classePersonagemMapper.fromCreateClasse(classePersonagemService.classePersonagemRepository.listarClassePorPersonagemID(idPersonagem)));
        return personagemDTO;
    }


    public void listar() throws BancoDeDadosException, SQLException {
        for (Personagem personagem : personagemRepository.listar()) {
            System.out.println(personagem);
        }
    }

    public void remover(PersonagemDTO personagem) throws BancoDeDadosException, SQLException {
        Personagem personagemRecuperado = personagemRepository.listarPorId(personagem.getId());
        PersonagemDTO personagemDTO = personagemMapper.toDTO(personagemRecuperado);
        personagemRepository.remover(personagemDTO.getId());
    }

    public PersonagemDTO listarPorId(Integer id) throws BancoDeDadosException, SQLException, NaoEncontradoException {
        Personagem personagemRecuperado = personagemRepository.listarPorId(id);
        if (personagemRecuperado == null) {
            throw new NaoEncontradoException("Personagem não encontrado");
        }
        PersonagemDTO personagemDTO = personagemMapper.toDTO(personagemRecuperado);
        return personagemDTO;
    }


    public void listarPersonagemsPorJogador(int idJogador) throws BancoDeDadosException, SQLException {
        List<Personagem> listaPersonagem = personagemRepository.listar().stream().filter(a -> Objects.equals(a.getIdJogador(), idJogador)).collect(Collectors.toList());
        for (Personagem personagem : listaPersonagem) {
            System.out.println(personagem);
        }
    }
}
