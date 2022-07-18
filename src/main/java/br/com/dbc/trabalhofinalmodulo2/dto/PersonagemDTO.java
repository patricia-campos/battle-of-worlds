package br.com.dbc.trabalhofinalmodulo2.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PersonagemDTO extends PersonagemCreateDTO {

    @NotNull
    @Schema(description = "Id do personagem")
    private Integer id;

    @NotNull
    @Schema(description = "Id do Jogador")
    private int idJogador;

    @NotNull
    @Schema(description = "Classe Personagem")
    ClassePersonagemPostDTO classePersonagem;

}
