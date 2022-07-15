package br.com.dbc.trabalhofinalmodulo2.mapper;


import br.com.dbc.trabalhofinalmodulo2.model.dto.CenarioCreateDTO;
import br.com.dbc.trabalhofinalmodulo2.model.dto.CenarioDTO;
import br.com.dbc.trabalhofinalmodulo2.model.entities.Cenario;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CenarioMapper {

    @Autowired
    private ObjectMapper objectMapper;

    public CenarioDTO toCreateDTO(Cenario cenario) {
        return objectMapper.convertValue(cenario, CenarioDTO.class);
    }

    public Cenario fromCreateDTO(CenarioCreateDTO cenarioCreateDTO) {
        return objectMapper.convertValue(cenarioCreateDTO, Cenario.class);
    }

}
