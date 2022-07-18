package br.com.dbc.trabalhofinalmodulo2.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class PersonagemPutDTO {

    @NotEmpty
    @NotBlank
    @Schema(description = "Nome do personagem")
    private String nomePersonagem;

}
