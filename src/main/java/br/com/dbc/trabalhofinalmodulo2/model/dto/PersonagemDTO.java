package br.com.dbc.trabalhofinalmodulo2.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
public class PersonagemDTO extends PersonagemCreateDTO {

    @NotNull
    private Integer id;

    @NotNull
    private int idJogador;

}
