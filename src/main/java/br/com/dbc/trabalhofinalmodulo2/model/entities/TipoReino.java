package br.com.dbc.trabalhofinalmodulo2.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum TipoReino {


    LUZ("LUZ", 1),
    SOMBRAS("SOMBRAS", 2),
    LUA("LUA", 3),
    MARTE("MARTE", 4);

    private String tipo;
    private int id;


    public String getTipo() {
        return tipo;
    }

    public static TipoReino ofTipo(Integer tipo){
        return Arrays.stream(TipoReino.values())
                .filter(tp -> tp.getTipo().equals(tipo))
                .findFirst()
                .get();
    }

    public static TipoReino ofTipo(String tipo){
        return Arrays.stream(TipoReino.values())
                .filter(tp -> tp.getTipo().equals(tipo))
                .findFirst()
                .get();
    }

}
