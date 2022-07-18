package br.com.dbc.trabalhofinalmodulo2.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class JogadorDTO extends JogadorCreateDTO {

    @Schema(description = "id do jogador")
    @NotNull
    private Integer id;

}
