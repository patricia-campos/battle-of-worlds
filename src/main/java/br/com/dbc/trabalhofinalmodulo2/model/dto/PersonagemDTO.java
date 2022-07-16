package br.com.dbc.trabalhofinalmodulo2.model.dto;

import br.com.dbc.trabalhofinalmodulo2.model.entities.ClassePersonagem;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PersonagemDTO extends PersonagemCreateDTO {

    @NotNull
    private Integer id;

    @NotNull
    private int idJogador;

    @NotNull
    ClassePersonagem classePersonagem;

}
