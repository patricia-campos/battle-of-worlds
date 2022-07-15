package br.com.dbc.trabalhofinalmodulo2.mapper;

import br.com.dbc.trabalhofinalmodulo2.model.dto.ClassePersonagemCreateDTO;
import br.com.dbc.trabalhofinalmodulo2.model.entities.ClassePersonagem;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClassePersonagemMapper {

    @Autowired
    private ObjectMapper objectMapper;


    public ClassePersonagemCreateDTO toClassePersonagemDTO(ClassePersonagem classePersonagem) {
        return objectMapper.convertValue(classePersonagem, ClassePersonagemCreateDTO.class);
    }

    public ClassePersonagem fromCreateDTO(ClassePersonagemCreateDTO classePersonagemCreateDTO) {
        return objectMapper.convertValue(classePersonagemCreateDTO, ClassePersonagem.class);
    }
}
