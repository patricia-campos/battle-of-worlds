package br.com.dbc.trabalhofinalmodulo2.service;

import br.com.dbc.trabalhofinalmodulo2.exceptions.BancoDeDadosException;
import br.com.dbc.trabalhofinalmodulo2.exceptions.NaoEncontradoException;
import br.com.dbc.trabalhofinalmodulo2.mapper.ClassePersonagemMapper;
import br.com.dbc.trabalhofinalmodulo2.mapper.PersonagemMapper;
import br.com.dbc.trabalhofinalmodulo2.model.dto.PersonagemClasseDTO;
import br.com.dbc.trabalhofinalmodulo2.model.dto.PersonagemDTO;
import br.com.dbc.trabalhofinalmodulo2.model.entities.ClassePersonagem;
import br.com.dbc.trabalhofinalmodulo2.model.entities.Personagem;
import br.com.dbc.trabalhofinalmodulo2.model.entities.TipoClassePersonagem;
import br.com.dbc.trabalhofinalmodulo2.repository.ClassePersonagemRepository;
import br.com.dbc.trabalhofinalmodulo2.repository.PersonagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;


@Service
public class PersonagemClasseService {

    @Autowired
    PersonagemRepository personagemRepository;

    @Autowired
    ClassePersonagemRepository classePersonagemRepository;

    @Autowired
    ClassePersonagemMapper classePersonagemMapper;

    @Autowired
    PersonagemMapper personagemMapper;

    @Autowired
    private PersonagemService personagemService;

    public PersonagemDTO adicionarPersonagemComClasse(PersonagemClasseDTO personagemClasseDTO) throws BancoDeDadosException, SQLException, NaoEncontradoException {
        ClassePersonagem classePersonagem = classePersonagemMapper.fromCreateClasse(personagemClasseDTO);
        if (TipoClassePersonagem.MAGO == personagemClasseDTO.getPersonagemClasseCreateDTO().getTipoClassePersonagem()) {
            classePersonagem.setVidaClasse(800.0);
            classePersonagem.setAtaqueClasse(200.0);
            classePersonagem.setDefesaClasse(100.0);
        } else if(TipoClassePersonagem.GUERREIRO == personagemClasseDTO.getPersonagemClasseCreateDTO().getTipoClassePersonagem()){
            classePersonagem.setVidaClasse(800.0);
            classePersonagem.setAtaqueClasse(100.0);
            classePersonagem.setDefesaClasse(200.0);
        }else if(TipoClassePersonagem.ELFO == personagemClasseDTO.getPersonagemClasseCreateDTO().getTipoClassePersonagem()){
            classePersonagem.setVidaClasse(800.0);
            classePersonagem.setAtaqueClasse(150.0);
            classePersonagem.setDefesaClasse(150.0);
        }
        classePersonagem.setTipoClassePersonagem(personagemClasseDTO.getPersonagemClasseCreateDTO().getTipoClassePersonagem());


        Personagem personagem = personagemMapper.fromCreateDTO(personagemService.listarPorId(classePersonagem.getIdPersonagem()));
        PersonagemDTO personagemDTO = personagemMapper.toDTO(personagem);
        
        personagemDTO.setClassePersonagem(classePersonagemMapper.fromCreateClasse(classePersonagem));
        personagemDTO.getClassePersonagem().setTipoPersonagem(personagemClasseDTO.getPersonagemClasseCreateDTO().getTipoClassePersonagem());

        classePersonagemRepository.adicionar(classePersonagem,personagem.getId());
        personagemDTO.getClassePersonagem().setIdClassePersonagem(classePersonagem.getIdClassePersonagem());

        return personagemDTO;
    }
}
