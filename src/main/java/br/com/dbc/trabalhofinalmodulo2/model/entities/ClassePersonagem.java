package br.com.dbc.trabalhofinalmodulo2.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ClassePersonagem {
    private int idClassePersonagem;
    private int idPersonagem;
    private TipoClassePersonagem tipoClassePersonagem;
    private Double vidaClasse;
    private Double defesaClasse;
    private Double ataqueClasse;

}



