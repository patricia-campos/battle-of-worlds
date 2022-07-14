package br.com.dbc.trabalhofinalmodulo2.exceptions;

public class BancoDeDadosException extends Throwable {

    public BancoDeDadosException(Throwable cause) {
        System.out.println(cause);
    }

    public BancoDeDadosException(String mensagem) {
        System.out.println(mensagem);
    }

}
