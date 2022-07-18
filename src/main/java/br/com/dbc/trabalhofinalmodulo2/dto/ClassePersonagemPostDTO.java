package br.com.dbc.trabalhofinalmodulo2.dto;

import br.com.dbc.trabalhofinalmodulo2.entities.TipoClassePersonagem;
import lombok.Data;

@Data
public class ClassePersonagemPostDTO {

    private int idClassePersonagem;
    private TipoClassePersonagem tipoPersonagem;
    private Double vidaClasse;
    private Double defesaClasse;
    private Double ataqueClasse;
}
