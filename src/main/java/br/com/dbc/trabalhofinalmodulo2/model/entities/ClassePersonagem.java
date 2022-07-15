package br.com.dbc.trabalhofinalmodulo2.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ClassePersonagem {
    private Integer idClassePersonagem;
    private Integer idPersonagem;
    private String nomeClassePersonagem;
    private Integer vidaClasse;
    private Integer defesaClasse;
    private Integer ataqueClasse;

}


//TODO - COMENTADO NA ClassePersonagemService OS MÉTODOS DE RETORNO
//TODO - REVISAR MÉTODOS COMENTADOS ABAIXO

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

    public ClassePersonagem(int id, String nomeClassePersonagem, int vidaClasse, int defesaClasse, int ataqueClasse, int idPersonagem) {
        this.idClassePersonagem = id;
        this.nomeClassePersonagem = nomeClassePersonagem;
        this.vidaClasse = vidaClasse;
        this.defesaClasse = defesaClasse;
        this.ataqueClasse = ataqueClasse;
        this.idPersonagem = idPersonagem;
    }



    public void setVidaClasse(int vidaClasse) {
        if (vidaClasse < 0) {
            this.vidaClasse = 0;
        } else {
            this.vidaClasse = vidaClasse;
        }
    }
*/


