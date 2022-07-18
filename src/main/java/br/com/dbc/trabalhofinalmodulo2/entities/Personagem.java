package br.com.dbc.trabalhofinalmodulo2.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Personagem {

    private int id;
    private int idJogador;
    private String nomePersonagem;

}

