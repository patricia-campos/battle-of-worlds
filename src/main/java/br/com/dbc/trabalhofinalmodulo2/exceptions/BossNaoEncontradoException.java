package br.com.dbc.trabalhofinalmodulo2.exceptions;

public class BossNaoEncontradoException extends Exception{

    public BossNaoEncontradoException(String mensagem) {
        System.out.println(mensagem);
    }
}
