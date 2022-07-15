package br.com.dbc.trabalhofinalmodulo2.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ClassePersonagemCreateDTO {

    // TODO - Em construção

    @Schema(description = "Id do personagem")
    @NotNull(message = "Id do personagem não pode ser nulo")
    private int idPersonagem;

    @Schema(description = "Nome da classe do personagem")
    @NotEmpty(message = "Nome da classe personagem não pode estar vazia")
    @NotBlank(message = "Nome da classe do personagem não pode estar em branco")
    @NotNull(message = "Nome da classe do personagem cenário não pode ser nulo")
    @Size(max = 200, message = "Nome da classe de personagem deve conter no máximo 200 caracteres")
    private String nomeClassePersonagem;

    @NotNull
    private int vidaClasse;

    @NotNull
    private int defesaClasse;

    @NotNull
    private int ataqueClasse;

}
