package br.com.dbc.trabalhofinalmodulo2.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Cenario {

    private Integer idCenario;
    private String nomeCenario;
    private String tipoCenario;
    private Date horario;

}

