package br.com.dbc.trabalhofinalmodulo2.mapper;

import br.com.dbc.trabalhofinalmodulo2.model.dto.JogadorCreateDTO;
import br.com.dbc.trabalhofinalmodulo2.model.dto.JogadorDTO;
import br.com.dbc.trabalhofinalmodulo2.model.entities.Jogador;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JogadorMapper {

    @Autowired
    private ObjectMapper objectMapper;

    public JogadorDTO toDTO(Jogador jogador) {
        return objectMapper.convertValue(jogador, JogadorDTO.class);
    }
    public Jogador fromCreateDTO(JogadorCreateDTO jogadorCreateDTO) {
        return objectMapper.convertValue(jogadorCreateDTO, Jogador.class);
    }

    public Jogador fromCreateDTO(JogadorDTO jogadorDTO) {
        return objectMapper.convertValue(jogadorDTO, Jogador.class);
    }
}
