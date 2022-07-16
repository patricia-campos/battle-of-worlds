package br.com.dbc.trabalhofinalmodulo2.service;

import br.com.dbc.trabalhofinalmodulo2.exceptions.BancoDeDadosException;
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



    public PersonagemDTO adicionarPersonagemComClasse(PersonagemClasseDTO personagemClasseDTO) throws BancoDeDadosException {
        ClassePersonagem classePersonagem = classePersonagemMapper.fromCreateClasse(personagemClasseDTO);
        if (TipoClassePersonagem.MAGO == personagemClasseDTO.getClassePersonagemCreateDTO().getTipoClassePersonagem()) {
            classePersonagem.setVidaClasse(800.0);
            classePersonagem.setAtaqueClasse(200.0);
            classePersonagem.setDefesaClasse(100.0);
        } else if(TipoClassePersonagem.GUERREIRO == personagemClasseDTO.getClassePersonagemCreateDTO().getTipoClassePersonagem()){
            classePersonagem.setVidaClasse(800.0);
            classePersonagem.setAtaqueClasse(100.0);
            classePersonagem.setDefesaClasse(200.0);
        }else if(TipoClassePersonagem.ELFO == personagemClasseDTO.getClassePersonagemCreateDTO().getTipoClassePersonagem()){
            classePersonagem.setVidaClasse(800.0);
            classePersonagem.setAtaqueClasse(150.0);
            classePersonagem.setDefesaClasse(150.0);
        }

        Personagem personagem = personagemRepository.listarPorId(classePersonagem.getIdPersonagem());
        PersonagemDTO personagemDTO = personagemMapper.toDTO(personagem);
        
        personagemDTO.setClassePersonagem(classePersonagem);
        personagemDTO.getClassePersonagem().setTipoPersonagem(personagemClasseDTO.getClassePersonagemCreateDTO().getTipoClassePersonagem());

        personagemRepository.adicionar(personagem,personagem.getIdJogador());
        classePersonagemRepository.adicionar(classePersonagem,personagem.getId());

        return personagemDTO;
    }

}
