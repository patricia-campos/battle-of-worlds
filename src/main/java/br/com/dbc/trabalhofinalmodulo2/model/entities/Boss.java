package br.com.dbc.trabalhofinalmodulo2.model.entities;

public class Boss {

    private int idBoss;
    private String nome;
    private int vida;
    private int ataque;
    private int defesa;

    public Boss() {
    }

    public Boss(String nome) {
        this.nome = nome;
        this.vida = 300;
        this.ataque = 30;
        this.defesa = 25;
    }

    public Boss(int id, String nome) {
        this.setNome(nome);
        this.setIdBoss(id);
        this.nome = nome;
        this.vida = 300;
        this.ataque = 30;
        this.defesa = 25;
    }

    public void setIdBoss(Integer idBoss) {
        this.idBoss = idBoss;
    }

    public int getIdBoss() { return idBoss; }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        if (vida < 0) {
            this.vida = 0;
        }else {
            this.vida = vida;
    }}

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getDefesa() {
        return defesa;
    }

    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }

    @Override
    public String toString() {
        return "Boss{" +
                "  nome='" + nome + '\'' +
                ", vida=" + vida +
                ", ataque=" + ataque +
                ", defesa=" + defesa +
                '}';
    }
}
