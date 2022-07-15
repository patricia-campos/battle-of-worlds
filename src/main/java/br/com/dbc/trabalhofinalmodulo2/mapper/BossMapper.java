package br.com.dbc.trabalhofinalmodulo2.mapper;

import br.com.dbc.trabalhofinalmodulo2.model.dto.BatalhaCreateDTO;
import br.com.dbc.trabalhofinalmodulo2.model.dto.BatalhaDTO;
import br.com.dbc.trabalhofinalmodulo2.model.dto.BossCreateDTO;
import br.com.dbc.trabalhofinalmodulo2.model.dto.BossDTO;
import br.com.dbc.trabalhofinalmodulo2.model.entities.Batalha;
import br.com.dbc.trabalhofinalmodulo2.model.entities.Boss;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BossMapper {


    @Autowired
    private ObjectMapper objectMapper;

    public BossCreateDTO toCreateDTO(Boss boss) {
        return objectMapper.convertValue(boss, BossCreateDTO.class);
    }

    public BossDTO toBossDTO(Boss boss) {
        return objectMapper.convertValue(boss, BossDTO.class);
    }

    public Boss toBoss(BossCreateDTO bossCreateDTO) {
        return objectMapper.convertValue(bossCreateDTO, Boss.class);
    }



}
