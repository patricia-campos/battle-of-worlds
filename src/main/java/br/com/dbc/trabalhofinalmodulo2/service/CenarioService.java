package br.com.dbc.trabalhofinalmodulo2.service;

import br.com.dbc.trabalhofinalmodulo2.exceptions.BancoDeDadosException;
import br.com.dbc.trabalhofinalmodulo2.mapper.BossMapper;
import br.com.dbc.trabalhofinalmodulo2.mapper.CenarioMapper;
import br.com.dbc.trabalhofinalmodulo2.model.dto.CenarioCreateDTO;
import br.com.dbc.trabalhofinalmodulo2.model.dto.CenarioDTO;
import br.com.dbc.trabalhofinalmodulo2.model.entities.Cenario;
import br.com.dbc.trabalhofinalmodulo2.repository.CenarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CenarioService {

    @Autowired
    CenarioRepository cenarioRepository;

    @Autowired
    CenarioMapper cenarioMapper;



    public CenarioDTO adicionar(CenarioCreateDTO cenario) throws BancoDeDadosException {
                return cenarioMapper.toCreateDTO(cenarioRepository.adicionar(cenarioMapper.fromCreateDTO(cenario)));
    }


    /*
    //TODO RETORNA CENÁRIO
    public Cenario retornaCenario(String tipo) throws BancoDeDadosException {
        return cenarioRepository.listar()
                .stream()
                .filter(a -> Objects.equals(a.getTipoCenario(), tipo))
                .map(a -> new Cenario(a.getIdCenario(), a.getNomeCenario(), a.getTipoCenario(), a.getHorario()))
                .findFirst()
                .orElse(null);
    }

     */


    public CenarioDTO editar(CenarioCreateDTO cenario) throws BancoDeDadosException {

        return cenarioMapper
                .toCreateDTO(cenarioRepository.editar(cenarioMapper
                                .fromCreateDTO(cenario)
                                .getIdCenario(), cenarioMapper.fromCreateDTO(cenario)));
    }


    public void remover(int id) throws BancoDeDadosException {

        cenarioRepository.remover(id);
    }

}
