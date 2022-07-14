package br.com.dbc.trabalhofinalmodulo2.service;

import br.com.dbc.trabalhofinalmodulo2.exceptions.BancoDeDadosException;
import br.com.dbc.trabalhofinalmodulo2.model.entities.Boss;
import br.com.dbc.trabalhofinalmodulo2.repository.BossRepository;

import java.util.Objects;

public class BossService {
    BossRepository bossRepository = new BossRepository();

    //Lista e exibe os Bosses cadastrados - READ
    public void listar() throws BancoDeDadosException {
        for (Boss boss2 : bossRepository.listar()) {
            System.out.println(boss2);
        }
    }

    public void adicionar(Boss boss) throws BancoDeDadosException {
        if (boss == null) {
            System.out.println("Boss inexistente");
        }else {
            bossRepository.adicionar(boss);
        }
    }

    public void remover(Boss boss) throws BancoDeDadosException {
        if (boss == null) {
            System.out.println("Boss inexistente");
        } else {
            bossRepository.remover(boss.getIdBoss());
        }
    }

    public void editar(Boss boss) throws BancoDeDadosException {
        assert boss != null;
        bossRepository.editar(boss.getIdBoss(), boss);

    }

    public Boss retornaBoss(String nome) throws BancoDeDadosException {
        return bossRepository.listar()
                .stream()
                .filter(a -> Objects.equals(a.getNome(), nome))
                .map(a -> new Boss(a.getIdBoss(), a.getNome()))
                .findFirst()
                .orElse(null);
    }

}
