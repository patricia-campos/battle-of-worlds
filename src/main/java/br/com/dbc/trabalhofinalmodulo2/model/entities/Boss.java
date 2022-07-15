package br.com.dbc.trabalhofinalmodulo2.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Boss {

    private int idBoss;
    private String nome;
    private Double vida;
    private Double ataque;
    private Double defesa;

}
