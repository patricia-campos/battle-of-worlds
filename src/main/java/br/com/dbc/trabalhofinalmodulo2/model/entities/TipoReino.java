package br.com.dbc.trabalhofinalmodulo2.model.entities;

import java.util.Arrays;

public enum TipoReino {


    LUZ("1"),
    SOMBRAS("2"),
    LUA("3"),
    MARTE("4");

    private String tipo;

    TipoReino(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public static TipoReino ofTipo(String tipo){
        return Arrays.stream(TipoReino.values())
                .filter(tp -> tp.getTipo().equals(tipo))
                .findFirst()
                .get();
    }

}
