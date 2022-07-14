package br.com.dbc.trabalhofinalmodulo2.service;

import br.com.dbc.trabalhofinalmodulo2.exceptions.BancoDeDadosException;
import br.com.dbc.trabalhofinalmodulo2.model.entities.Batalha;
import br.com.dbc.trabalhofinalmodulo2.repository.BatalhaRepository;

public class BatalhaService {

    BatalhaRepository batalhaRepository = new BatalhaRepository();

    public void adicionar(Batalha batalha) throws BancoDeDadosException {
        if (batalha == null) {
            System.out.println("Batalha não iniciada.");
        } else {
            batalhaRepository.adicionar(batalha);
        }
    }

    public void listar() throws BancoDeDadosException {
        for (Batalha batalha2 : batalhaRepository.listar()) {
            System.out.println(batalha2);
        }
    }

    //No momento do projeto não está implementado
    public void remover(Batalha batalha) throws BancoDeDadosException {
        if (batalha == null) {
            System.out.println("Não existe batalha para ser removida");
        } else {
            batalhaRepository.remover(batalha.getIdBatalha());
        }
    }

    //No momento do projeto não está implementado
    public void editar(Integer id, Batalha batalha) {

        try {
            boolean conseguiuEditar = batalhaRepository.editar(id, batalha);
            System.out.println("Novo round");

        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

}
