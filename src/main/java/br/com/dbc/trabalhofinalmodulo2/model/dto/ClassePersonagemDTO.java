package br.com.dbc.trabalhofinalmodulo2.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ClassePersonagemDTO extends ClassePersonagemCreateDTO{

    @Schema(description = "ID - Identificador único da classe do personagem")
    @NotNull(message = "Id do personagem não pode ser nulo")
    private int idClassePersonagem;
}
