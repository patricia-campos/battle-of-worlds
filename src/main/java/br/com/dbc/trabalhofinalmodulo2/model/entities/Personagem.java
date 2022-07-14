package br.com.dbc.trabalhofinalmodulo2.model.entities;

public class Personagem {

    private int id;
    private int idJogador;
    private String nomePersonagem;
    private ClassePersonagem classePersonagem = new ClassePersonagem();

    public int getIdJogador() {
        return idJogador;
    }

    public void setIdJogador(int idJogador) {
        this.idJogador = idJogador;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomePersonagem() {
        return nomePersonagem;
    }

    public void setNomePersonagem(String nomePersonagem) {
        this.nomePersonagem = nomePersonagem;
    }

    public ClassePersonagem getClassePersonagem() {
        return classePersonagem;
    }

    public void setClassePersonagem(ClassePersonagem classePersonagem) {
        this.classePersonagem = classePersonagem;
    }

    public Personagem() {
    }

    public Personagem(int id,String nomePersonagem) {
        this.setNomePersonagem(nomePersonagem);
        this.setId(id);
    }
    public Personagem(String nomePersonagem) {
        this.setNomePersonagem(nomePersonagem);
    }

    @Override
    public String toString() {
        return "========================="
                + "\nNome do seu personagem: " + nomePersonagem +
                "\n=========================";
    }
}

