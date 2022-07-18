package br.com.dbc.trabalhofinalmodulo2.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PersonagemDTO extends PersonagemCreateDTO {

    @NotNull
    private Integer id;

    @NotNull
    private int idJogador;

    @NotNull
    ClassePersonagemPostDTO classePersonagem;

}
