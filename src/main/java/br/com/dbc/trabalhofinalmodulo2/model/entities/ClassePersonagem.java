package br.com.dbc.trabalhofinalmodulo2.model.entities;


import br.com.dbc.trabalhofinalmodulo2.interfaces.Impressao;

import java.util.Objects;

public class ClassePersonagem implements Impressao {
    private int idClassePersonagem;
    private int idPersonagem;
    private String nomeClassePersonagem;
    private int vidaClasse;
    private int defesaClasse;
    private int ataqueClasse;

    public int getIdPersonagem() {
        return idPersonagem;
    }

    public void setIdPersonagem(int idPersonagem) {
        this.idPersonagem = idPersonagem;
    }

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

    public int getIdClassePersonagem() {
        return idClassePersonagem;
    }

    public void setIdClassePersonagem(int idClassePersonagem) {
        this.idClassePersonagem = idClassePersonagem;
    }

    public String getNomeClassePersonagem() {
        return nomeClassePersonagem;
    }

    public void setNomeClassePersonagem(String nomeClassePersonagem) {
        this.nomeClassePersonagem = nomeClassePersonagem;
    }

    public ClassePersonagem() {
    }

    public int getVidaClasse() {
        return vidaClasse;
    }

    public void setVidaClasse(int vidaClasse) {
        if (vidaClasse < 0) {
            this.vidaClasse = 0;
        } else {
            this.vidaClasse = vidaClasse;
        }
    }

    public int getDefesaClasse() {
        return defesaClasse;
    }

    public void setDefesaClasse(int defesaClasse) {
        this.defesaClasse = defesaClasse;
    }

    public int getAtaqueClasse() {
        return ataqueClasse;
    }

    public void setAtaqueClasse(int ataqueClasse) {
        this.ataqueClasse = ataqueClasse;
    }


    @Override
    public String toString() {
        return "\n" +
                "" +
                "| Nome da classe: " + nomeClassePersonagem +
                "\n" +
                "| Vida da classe: " + vidaClasse
                + "\n" +
                "| Defesa da classe: " + defesaClasse
                + "\n" +
                "| Ataque da classe: " + ataqueClasse +
                "";
    }

    @Override
    public void imprimir() {
        System.out.println(this);
    }
}
