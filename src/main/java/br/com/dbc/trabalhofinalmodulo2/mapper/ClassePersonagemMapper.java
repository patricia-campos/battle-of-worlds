package br.com.dbc.trabalhofinalmodulo2.mapper;

import br.com.dbc.trabalhofinalmodulo2.model.dto.ClassePersonagemCreateDTO;
import br.com.dbc.trabalhofinalmodulo2.model.dto.ClassePersonagemDTO;
import br.com.dbc.trabalhofinalmodulo2.model.dto.PersonagemClasseDTO;
import br.com.dbc.trabalhofinalmodulo2.model.dto.PersonagemDTO;
import br.com.dbc.trabalhofinalmodulo2.model.entities.ClassePersonagem;
import br.com.dbc.trabalhofinalmodulo2.model.entities.Personagem;
import br.com.dbc.trabalhofinalmodulo2.service.ClassePersonagemPostDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClassePersonagemMapper {

    @Autowired
    private ObjectMapper objectMapper;


    public ClassePersonagemDTO toClassePersonagemDTO(ClassePersonagem classePersonagem) {
        return objectMapper.convertValue(classePersonagem, ClassePersonagemDTO.class);
    }

    public ClassePersonagem fromCreateDTO(ClassePersonagemCreateDTO classePersonagemCreateDTO) {
        return objectMapper.convertValue(classePersonagemCreateDTO, ClassePersonagem.class);
    }

    public ClassePersonagem fromCreateClasse(PersonagemClasseDTO classePersonagemCreateDTO) {
        return objectMapper.convertValue(classePersonagemCreateDTO, ClassePersonagem.class);
    }


    public ClassePersonagemPostDTO fromCreateClasse(ClassePersonagem classePersonagem) {
        return objectMapper.convertValue(classePersonagem, ClassePersonagemPostDTO.class);
    }

    public ClassePersonagemDTO fromCreateDTO(ClassePersonagem classePersonagem) {
        return objectMapper.convertValue(classePersonagem, ClassePersonagemDTO.class);
    }

    public ClassePersonagemPostDTO fromCreateDTOClasse(ClassePersonagem classePersonagem) {
        return objectMapper.convertValue(classePersonagem, ClassePersonagemPostDTO.class);
    }

}
