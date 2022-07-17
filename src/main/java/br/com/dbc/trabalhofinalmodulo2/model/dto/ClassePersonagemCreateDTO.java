package br.com.dbc.trabalhofinalmodulo2.model.dto;

import br.com.dbc.trabalhofinalmodulo2.model.entities.TipoClassePersonagem;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class ClassePersonagemCreateDTO {


    @Schema(description = "Nome da classe do personagem")
    @NotNull(message = "Nome da classe do personagem não pode ser nulo")
    private TipoClassePersonagem tipoClassePersonagem;

    @Schema(description = "Vida da classe do personagem")
    @NotNull(message = "Vida da classe do personagem não pode ser nulo")
    private Double vidaClasse;

    @Schema(description = "Defesa da classe do personagem")
    @NotNull(message = "Defesa da classe do personagem não pode ser nulo")
    private Double defesaClasse;

    @Schema(description = "Ataque da classe do personagem")
    @NotNull(message = "Ataque da classe do personagem não pode ser nulo")
    private Double ataqueClasse;

}
