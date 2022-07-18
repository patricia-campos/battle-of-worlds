package br.com.dbc.trabalhofinalmodulo2.dto;

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

    @NotNull(message = "O nome do boss não pode ser nulo")
    @NotEmpty(message = "Nome do boss não pode ser vazio")
    @Schema(description = "Nome do Boss")
    private String nome;

    @NotNull(message = "A vida do boss não pode ser nula")
    @Schema(description = "Vida do boss")
    private Double vida;

    @NotNull(message = "O ataque do boss não pode ser nulo")
    @Schema(description = "Ataque do boss")
    private Double ataque;

    @NotNull(message = "O defesa do boss não pode ser nulo")
    @Schema(description = "Defesa do boss")
    private Double defesa;

}
