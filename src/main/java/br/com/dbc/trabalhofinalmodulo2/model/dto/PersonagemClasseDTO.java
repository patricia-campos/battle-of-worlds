package br.com.dbc.trabalhofinalmodulo2.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PersonagemClasseDTO {


    @Schema(description = "Id do personagem")
    @NotNull(message = "Id do personagem não pode ser nulo")
    private int idPersonagem;

    @Schema(description = "Classe do personagem")
    @NotNull(message = "Classe do personagem não pode ser nulo")
    ClassePersonagemCreateDTO classePersonagemCreateDTO;
}
