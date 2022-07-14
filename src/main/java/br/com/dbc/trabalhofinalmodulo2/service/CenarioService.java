package br.com.dbc.trabalhofinalmodulo2.service;

import br.com.dbc.trabalhofinalmodulo2.exceptions.BancoDeDadosException;
import br.com.dbc.trabalhofinalmodulo2.model.entities.Cenario;
import br.com.dbc.trabalhofinalmodulo2.repository.CenarioRepository;

import java.util.Objects;

public class CenarioService {

    CenarioRepository cenarioRepository = new CenarioRepository();

    public void adicionarCenario(Cenario cenario) throws BancoDeDadosException {
        if (cenario == null) {
            System.out.println("Cenario inexistente");
        } else {
            cenarioRepository.adicionar(cenario);
        }
    }

    public void remover(Cenario cenario) throws BancoDeDadosException {
        if (cenario == null) {
            System.out.println("Cenario inexistente");
        } else {
            cenarioRepository.remover(cenario.getIdCenario());
        }
    }

    public void editar(Cenario cenario) throws BancoDeDadosException {
        if (cenario == null) {
            System.out.println("Cenario inexistente");
        } else {
            cenarioRepository.editar(cenario.getIdCenario(), cenario);
        }
    }

    public Cenario retornaCenario(String tipo) throws BancoDeDadosException {
        return cenarioRepository.listar()
                .stream()
                .filter(a -> Objects.equals(a.getTipoCenario(), tipo))
                .map(a -> new Cenario(a.getIdCenario(), a.getNomeCenario(), a.getTipoCenario(), a.getHorario()))
                .findFirst()
                .orElse(null);
    }

}
