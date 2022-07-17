package br.com.dbc.trabalhofinalmodulo2.model.dto;

import br.com.dbc.trabalhofinalmodulo2.model.entities.TipoClassePersonagem;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
public class ClassePersonagemCreateDTO {


    @Schema(description = "Nome da classe do personagem")
    @NotNull(message = "Nome da classe do personagem cenário não pode ser nulo")
    private TipoClassePersonagem tipoClassePersonagem;
    @NotNull
    private Double vidaClasse;
    @NotNull
    private Double defesaClasse;
    @NotNull
    private Double ataqueClasse;


}
