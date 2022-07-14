package br.com.dbc.trabalhofinalmodulo2.model.entities;

public class Batalha {

    private int idBatalha;
    private int idCenario;
    private int idJogador;
    private int idBoss;
    private int roundBatalha;
    private String status;

    public Batalha() {

    }
    public int getIdBatalha() {
        return idBatalha;
    }

    public void setIdBatalha(int idBatalha) {
        this.idBatalha = idBatalha;
    }

    public int getIdCenario() {
        return idCenario;
    }

    public void setIdCenario(int idCenario) {
        this.idCenario = idCenario;
    }

    public int getIdJogador() {
        return idJogador;
    }

    public void setIdJogador(int idJogador) {
        this.idJogador = idJogador;
    }

    public int getIdBoss() {
        return idBoss;
    }

    public void setIdBoss(int idBoss) {
        this.idBoss = idBoss;
    }

    public int getRoundBatalha() {
        return roundBatalha;
    }

    public void setRoundBatalha(int roundBatalha) {
        this.roundBatalha = roundBatalha;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Batalha( int idCenario, int idJogador, int idBoss, int roundBatalha, String status) {
        this.idCenario = idCenario;
        this.idJogador = idJogador;
        this.idBoss = idBoss;
        this.roundBatalha = roundBatalha;
        this.status = status;
    }
}
