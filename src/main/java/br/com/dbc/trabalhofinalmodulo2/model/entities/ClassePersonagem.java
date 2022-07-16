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
    private TipoClassePersonagem tipoPersonagem;
    private Double vidaClasse;
    private Double defesaClasse;
    private Double ataqueClasse;

}

//TODO - REVISAR COMENTADOS ABAIXO

/*

    public ClassePersonagem(String nomeClassePersonagem) {
        if (Objects.equals(nomeClassePersonagem.toUpperCase(), "MAGO")) {
            this.nomeClassePersonagem = nomeClassePersonagem;
            this.vidaClasse = 150;
            this.defesaClasse = 40;
            this.ataqueClasse = 50;
        } else if (Objects.equals(nomeClassePersonagem.toUpperCase(), "ELFO")) {
            this.nomeClassePersonagem = nomeClassePersonagem;
            this.vidaClasse = 150;
            this.defesaClasse = 50;
            this.ataqueClasse = 50;
        } else if (Objects.equals(nomeClassePersonagem.toUpperCase(), "GUERREIRO")) {
            this.nomeClassePersonagem = nomeClassePersonagem;
            this.vidaClasse = 150;
            this.defesaClasse = 50;
            this.ataqueClasse = 40;
        }
    }



    public void setVidaClasse(int vidaClasse) {
        if (vidaClasse < 0) {
            this.vidaClasse = 0;
        } else {
            this.vidaClasse = vidaClasse;
        }
    }
*/


