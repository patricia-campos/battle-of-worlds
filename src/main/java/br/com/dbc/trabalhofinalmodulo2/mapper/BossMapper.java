package br.com.dbc.trabalhofinalmodulo2.mapper;

import br.com.dbc.trabalhofinalmodulo2.dto.BossCreateDTO;
import br.com.dbc.trabalhofinalmodulo2.dto.BossDTO;
import br.com.dbc.trabalhofinalmodulo2.entities.Boss;
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

    public Boss toCreateFromBoss(BossCreateDTO bossCreateDTO) {
        return objectMapper.convertValue(bossCreateDTO, Boss.class);
    }

}
