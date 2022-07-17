package br.com.dbc.trabalhofinalmodulo2.service;

import br.com.dbc.trabalhofinalmodulo2.model.entities.TipoClassePersonagem;
import lombok.Data;

@Data
public class ClassePersonagemPostDTO {

    private int idClassePersonagem;
    private TipoClassePersonagem tipoPersonagem;
    private Double vidaClasse;
    private Double defesaClasse;
    private Double ataqueClasse;
}
