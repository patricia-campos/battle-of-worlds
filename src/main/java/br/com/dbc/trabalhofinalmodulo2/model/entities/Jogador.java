package br.com.dbc.trabalhofinalmodulo2.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Jogador {

    private Integer id;
    private String nomeJogador;
    private String senha;
    private String email;
}
