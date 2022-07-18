package br.com.dbc.trabalhofinalmodulo2.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonagemCreateDTO {

    @NotEmpty
    @NotBlank
    @Schema(description = "Nome do personagem")
    private String nomePersonagem;
}
