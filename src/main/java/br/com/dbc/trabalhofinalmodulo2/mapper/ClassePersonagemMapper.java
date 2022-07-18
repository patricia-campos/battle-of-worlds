package br.com.dbc.trabalhofinalmodulo2.mapper;

import br.com.dbc.trabalhofinalmodulo2.dto.ClassePersonagemDTO;
import br.com.dbc.trabalhofinalmodulo2.dto.PersonagemClasseDTO;
import br.com.dbc.trabalhofinalmodulo2.entities.ClassePersonagem;
import br.com.dbc.trabalhofinalmodulo2.dto.ClassePersonagemPostDTO;
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

    public ClassePersonagem fromCreateClasse(PersonagemClasseDTO classePersonagemCreateDTO) {
        return objectMapper.convertValue(classePersonagemCreateDTO, ClassePersonagem.class);
    }

    public ClassePersonagemPostDTO fromCreateClasse(ClassePersonagem classePersonagem) {
        return objectMapper.convertValue(classePersonagem, ClassePersonagemPostDTO.class);
    }

    public ClassePersonagemPostDTO fromCreateDTOClasse(ClassePersonagem classePersonagem) {
        return objectMapper.convertValue(classePersonagem, ClassePersonagemPostDTO.class);
    }

}
