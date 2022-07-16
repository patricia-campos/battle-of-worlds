package br.com.dbc.trabalhofinalmodulo2.model.dto;

import br.com.dbc.trabalhofinalmodulo2.model.entities.TipoReino;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class CenarioCreateDTO {

    @NotEmpty(message = "Nome do cenário não pode estar vazio")
    @NotBlank(message = "Nome do cenário não pode estar em branco")
    @NotNull(message = "Nome do cenário não pode ser nulo")
    @Size(max = 200, message = "Nome do cenário deve conter no máximo 200 caracteres")
    private String nomeCenario;

    @NotEmpty(message = "Tipo de cenário não pode estar vazio")
    @NotBlank(message = "Tipo de cenário não pode estar em branco")
    @NotNull(message = "Tipo de cenário não pode ser nulo")
    @Size(max = 1, message = "Nome do cenário deve conter no máximo 1 caractere")
    private TipoReino tipoReino;

    @NotNull(message = "Horário não pode ser nulo")
    private Date horario;

}
