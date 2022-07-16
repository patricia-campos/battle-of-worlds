package br.com.dbc.trabalhofinalmodulo2.model.dto;

import br.com.dbc.trabalhofinalmodulo2.model.entities.TipoClassePersonagem;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class ClassePersonagemCreateDTO {


    @Schema(description = "Id do personagem")
    @NotNull(message = "Id do personagem não pode ser nulo")
    private int idPersonagem;

    @Schema(description = "Nome da classe do personagem")
    @NotNull(message = "Nome da classe do personagem cenário não pode ser nulo")
    private TipoClassePersonagem tipoClassePersonagem;

    @NotNull
    private int vidaClasse;

    @NotNull
    private int defesaClasse;

    @NotNull
    private int ataqueClasse;

}
