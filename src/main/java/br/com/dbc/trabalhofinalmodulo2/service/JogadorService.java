package br.com.dbc.trabalhofinalmodulo2.service;

import br.com.dbc.trabalhofinalmodulo2.exceptions.BancoDeDadosException;
import br.com.dbc.trabalhofinalmodulo2.model.entities.Jogador;
import br.com.dbc.trabalhofinalmodulo2.repository.JogadorRepository;

public class JogadorService {
    JogadorRepository jogadorRepository = new JogadorRepository();

    public void adicionar(Jogador jogador) throws BancoDeDadosException {
        if (jogador == null) {
            System.out.println("Jogador não encontrado");
        } else {
            if (this.verificaNomeJogador(jogador)) {
                jogadorRepository.adicionar(jogador);
            } else {
                System.out.println("Jogador ja cadastrado");
            }
        }
    }

    public void listarTodos() throws BancoDeDadosException {
     for(Jogador jogador: jogadorRepository.listar()){
         System.out.println(jogador);
     }
    }

    public void remover(Jogador jogador) throws BancoDeDadosException {


        if (jogador == null) {
            System.out.println("Jogador não encontrado");
        } else {
            jogadorRepository.remover(jogador.getId());
        }
    }

    public void editar(Jogador jogador, String nome) throws BancoDeDadosException {
        if (jogador == null) {
            System.out.println("Jogador não encontrado");
        } else {
            jogador.setNomeJogador(nome);
            if (this.verificaNomeJogador(jogador)) {
                jogadorRepository.editar(jogador.getId(), jogador);
            } else {
                System.out.println("Não possivel editar nome ja existente");
            }
        }
    }

    public Jogador retornaJogador(String nome) throws BancoDeDadosException {
        return jogadorRepository.listarPorNome(nome);
    }

    //Verifica se existe um jogador com o nome na minha base de dados, caso exista ele traz o objeto com valor e retorna false nao podendo alterar.
    public boolean verificaNomeJogador(Jogador jogador) throws BancoDeDadosException {
        Jogador jogador1 = this.retornaJogador(jogador.getNomeJogador());
            return jogador1 == null;
    }

}
