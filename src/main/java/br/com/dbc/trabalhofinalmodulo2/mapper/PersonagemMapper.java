package br.com.dbc.trabalhofinalmodulo2.mapper;

import br.com.dbc.trabalhofinalmodulo2.model.dto.PersonagemCreateDTO;
import br.com.dbc.trabalhofinalmodulo2.model.dto.PersonagemDTO;
import br.com.dbc.trabalhofinalmodulo2.model.dto.PersonagemPutDTO;
import br.com.dbc.trabalhofinalmodulo2.model.entities.Personagem;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonagemMapper {

    @Autowired
    private ObjectMapper objectMapper;

    public PersonagemDTO toDTO(Personagem personagem) {
        return objectMapper.convertValue(personagem, PersonagemDTO.class);
    }

    public Personagem fromCreateDTO(PersonagemCreateDTO personagemCreateDTO) {
        return objectMapper.convertValue(personagemCreateDTO, Personagem.class);
    }

    public Personagem fromCreateDTO(PersonagemDTO personagemDTO) {
        return objectMapper.convertValue(personagemDTO, Personagem.class);
    }

//    public PersonagemDTO toDTOPut(Personagem personagem) {
//        return objectMapper.convertValue(personagem, PersonagemDTO.class);
//    }

    public Personagem fromPutDTO(PersonagemPutDTO personagemPutDTODTO) {
        return objectMapper.convertValue(personagemPutDTODTO, Personagem.class);
    }
}
