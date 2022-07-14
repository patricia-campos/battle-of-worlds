package br.com.dbc.trabalhofinalmodulo2.model.entities;


import br.com.dbc.trabalhofinalmodulo2.interfaces.Impressao;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Cenario implements Impressao {

    private int idCenario;
    private String nomeCenario;
    private String tipoCenario;
    private Date horario;

    public Cenario() {
    }

    public Cenario(int idCenario, String nomeCenario, String tipoCenario, Date horario) {
        this.idCenario = idCenario;
        this.nomeCenario = nomeCenario;
        this.tipoCenario = tipoCenario;
        this.horario = horario;
    }
    public Cenario( String nomeCenario, String tipoCenario, Date horario) {
        this.nomeCenario = nomeCenario;
        this.tipoCenario = tipoCenario;
        this.horario = horario;
    }

    public String getTipoCenario() {
        return tipoCenario;
    }

    public void setTipoCenario(String tipoCenario) {
        this.tipoCenario = tipoCenario;
    }

    public int getIdCenario() {
        return idCenario;
    }

    public void setIdCenario(int idCenario) {
        this.idCenario = idCenario;
    }

    public String getNomeCenario() {
        return nomeCenario;
    }

    public void setNomeCenario(String nomeCenario) {
        this.nomeCenario = nomeCenario;
    }

    public Date getHorario() {
        return horario;
    }

    public void setHorario(Date horario) {
        this.horario = horario;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        return "Cenario:\n" +
                "" + nomeCenario +
                "\nHorario: " + sdf.format(horario);
    }

    @Override
    public void imprimir() {

    }
}
