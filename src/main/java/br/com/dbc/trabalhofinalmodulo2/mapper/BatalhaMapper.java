package br.com.dbc.trabalhofinalmodulo2.mapper;

import br.com.dbc.trabalhofinalmodulo2.dto.BatalhaCreateDTO;
import br.com.dbc.trabalhofinalmodulo2.dto.BatalhaDTO;
import br.com.dbc.trabalhofinalmodulo2.entities.Batalha;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BatalhaMapper {


    @Autowired
    private ObjectMapper objectMapper;

    public BatalhaCreateDTO toCreateDTO(Batalha batalha) {
        return objectMapper.convertValue(batalha, BatalhaCreateDTO.class);
    }

    public BatalhaDTO toBatalhaDTO(Batalha batalha) {
        return objectMapper.convertValue(batalha, BatalhaDTO.class);
    }

    public Batalha fromCreateDTO(BatalhaCreateDTO batalhaCreateDTO) {
        return objectMapper.convertValue(batalhaCreateDTO, Batalha.class);
    }



}
