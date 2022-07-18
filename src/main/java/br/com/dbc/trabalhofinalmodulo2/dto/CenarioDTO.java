package br.com.dbc.trabalhofinalmodulo2.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CenarioDTO extends CenarioCreateDTO{

    @Schema(description = "ID - Identificador único do cenário da batalha")
    @NotNull(message = "Id cenário não pode ser nulo")
    private int idCenario;
}
