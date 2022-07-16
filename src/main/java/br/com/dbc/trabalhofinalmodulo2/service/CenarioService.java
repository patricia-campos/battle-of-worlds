package br.com.dbc.trabalhofinalmodulo2.service;

import br.com.dbc.trabalhofinalmodulo2.exceptions.BancoDeDadosException;
import br.com.dbc.trabalhofinalmodulo2.mapper.CenarioMapper;
import br.com.dbc.trabalhofinalmodulo2.model.dto.CenarioCreateDTO;
import br.com.dbc.trabalhofinalmodulo2.model.dto.CenarioDTO;
import br.com.dbc.trabalhofinalmodulo2.repository.CenarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CenarioService {

    @Autowired
    CenarioRepository cenarioRepository;

    @Autowired
    CenarioMapper cenarioMapper;


    public CenarioDTO adicionar(CenarioCreateDTO cenario) throws BancoDeDadosException, SQLException {
                return cenarioMapper.toCreateDTO(cenarioRepository.adicionar(cenarioMapper.fromCreateDTO(cenario)));
    }


    public List<CenarioDTO> listar() throws BancoDeDadosException, SQLException {
        return cenarioRepository
                .listar()
                .stream()
                .map(x -> cenarioMapper.toCreateDTO(x))
                .collect(Collectors.toList());
    }


    //todo checar id no repository pq n passa como parametro id no controller
    public CenarioDTO editar(CenarioCreateDTO cenario) throws BancoDeDadosException, SQLException {

        return cenarioMapper
                .toCreateDTO(cenarioRepository.editar(cenarioMapper
                                .fromCreateDTO(cenario)
                                .getIdCenario(), cenarioMapper.fromCreateDTO(cenario)));
    }


    public void remover(int id) throws BancoDeDadosException, SQLException {

        cenarioRepository.remover(id);
    }

}
