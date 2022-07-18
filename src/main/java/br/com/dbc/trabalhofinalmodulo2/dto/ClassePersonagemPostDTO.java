package br.com.dbc.trabalhofinalmodulo2.dto;

import br.com.dbc.trabalhofinalmodulo2.entities.TipoClassePersonagem;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ClassePersonagemPostDTO {

    @Schema(description = "Id Classe Personagem")
    @NotNull(message = "Id do personagem não pode ser nulo")
    private int idClassePersonagem;

    @Schema(description = "Tipo de classe do personagem")
    @NotBlank(message = "Tipo do personagem é obrigatório")
    private TipoClassePersonagem tipoClassePersonagem;

    @Schema(description = "Vida da Classe")
    @NotBlank(message = "Vida do personagem é obrigatório")
    private Double vidaClasse;

    @Schema(description = "Defesa da classe")
    @NotBlank(message = "Defesa do personagem é obrigatório")
    private Double defesaClasse;

    @Schema(description = "Ataque da classe")
    @NotBlank(message = "Ataque do personagem é obrigatório")
    private Double ataqueClasse;
}
