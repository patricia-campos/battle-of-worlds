package br.com.dbc.trabalhofinalmodulo2.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Batalha {

    private int idBatalha;
    private int idCenario;
    private int idJogador;
    private int idBoss;
    private int roundBatalha;
    private String status;

}
