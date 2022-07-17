package br.com.dbc.trabalhofinalmodulo2.model.dto;

import br.com.dbc.trabalhofinalmodulo2.model.entities.TipoClassePersonagem;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ClassePersonagemCreateDTO {


    @Schema(description = "Nome da classe do personagem")
    @NotNull(message = "Nome da classe do personagem cenário não pode ser nulo")
    private TipoClassePersonagem tipoClassePersonagem;

    @Schema(description = "Vida do personagem")
    @NotNull(message = "Vida do personagem não pode ser nula")
    private Double vidaClasse;

    @Schema(description = "Defesa do personagem")
    @NotNull(message = "Defesa do personagem não pode ser nula")
    private Double defesaClasse;

    @Schema(description = "Ataque do personagem")
    @NotNull(message = "Ataque do personagem não pode ser nula")
    private Double ataqueClasse;

}
