package br.com.dbc.trabalhofinalmodulo2.model.entities;

public class Jogador {

    private int id;
    private String nomeJogador;
    private String senha;

    public String getSenha() {
        return senha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNomeJogador() {
        return nomeJogador;
    }

    public void setNomeJogador(String nomeJogador) {
        this.nomeJogador = nomeJogador;
    }

    public Jogador(String nomeJogador, String senha) {
        this.setNomeJogador(nomeJogador);
        this.setSenha(senha);
    }
    public Jogador(int id, String nomeJogador, String senha) {
        this.setNomeJogador(nomeJogador);
        this.setSenha(senha);
        this.setId(id);
    }
    public Jogador() {

    }

    @Override
    public String toString() {
        return "========================="
                + "\n" +
                "| Nome do Jogador: " + nomeJogador
                + "\n" +
                "=========================";
    }
}
