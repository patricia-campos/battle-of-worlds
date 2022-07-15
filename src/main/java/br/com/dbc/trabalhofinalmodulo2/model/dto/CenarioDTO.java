package br.com.dbc.trabalhofinalmodulo2.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CenarioDTO extends CenarioCreateDTO{

    @Schema(description = "ID - Identificador único do cenário da batalha")
    private int idCenario;
}
