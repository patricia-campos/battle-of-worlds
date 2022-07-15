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


    //TODO - COMENTADO NA CenarioService OS MÉTODOS DE RETORNO
    //TODO - REVISAR MÉTODOS COMENTADOS ABAIXO

    /*
    public Cenario() {
    }

    public Cenario(int idCenario, String nomeCenario, String tipoCenario, Date horario) {
        this.idCenario = idCenario;
        this.nomeCenario = nomeCenario;
        this.tipoCenario = tipoCenario;
        this.horario = horario;
    }
    public Cenario( String nomeCenario, String tipoCenario, Date horario) {
        this.nomeCenario = nomeCenario;
        this.tipoCenario = tipoCenario;
        this.horario = horario;
    }


     */

