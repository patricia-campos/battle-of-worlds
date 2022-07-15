package br.com.dbc.trabalhofinalmodulo2.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BossCreateDTO {

    @NotNull
    @NotEmpty
    @Schema(description = "Nome do Boss")
    private String nome;

    @NotNull
    @NotEmpty
    @Schema(description = "Vida do boss")
    private int vida;

    @NotNull
    @NotEmpty
    @Schema(description = "Ataque do boss")
    private int ataque;

    @NotNull
    @NotEmpty
    @Schema(description = "Defesa do boss")
    private int defesa;

}
