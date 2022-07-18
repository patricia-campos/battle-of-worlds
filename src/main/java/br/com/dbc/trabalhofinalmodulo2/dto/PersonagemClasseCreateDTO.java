package br.com.dbc.trabalhofinalmodulo2.dto;

import br.com.dbc.trabalhofinalmodulo2.entities.TipoClassePersonagem;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PersonagemClasseCreateDTO {

    @Schema(description = "Nome da classe do personagem")
    @NotNull(message = "Nome da classe do personagem não pode ser nulo")
    private TipoClassePersonagem tipoClassePersonagem;
}
