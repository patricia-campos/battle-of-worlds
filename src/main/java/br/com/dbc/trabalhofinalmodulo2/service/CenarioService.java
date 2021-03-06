package br.com.dbc.trabalhofinalmodulo2.service;

import br.com.dbc.trabalhofinalmodulo2.exceptions.BancoDeDadosException;
import br.com.dbc.trabalhofinalmodulo2.exceptions.NaoEncontradoException;
import br.com.dbc.trabalhofinalmodulo2.mapper.CenarioMapper;
import br.com.dbc.trabalhofinalmodulo2.dto.CenarioCreateDTO;
import br.com.dbc.trabalhofinalmodulo2.dto.CenarioDTO;
import br.com.dbc.trabalhofinalmodulo2.entities.Cenario;
import br.com.dbc.trabalhofinalmodulo2.repository.CenarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CenarioService {

    @Autowired
    private CenarioRepository cenarioRepository;

    @Autowired
    private CenarioMapper cenarioMapper;


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


    public CenarioDTO editar(CenarioCreateDTO cenario, int id) throws BancoDeDadosException, Exception {
        verificarCenario(id);
        return cenarioMapper
                .toCreateDTO(cenarioRepository.editar(id, cenarioMapper.fromCreateDTO(cenario)));
    }


    public void remover(int id) throws BancoDeDadosException, Exception {
        verificarCenario(id);
        cenarioRepository.remover(id);
    }

    public void verificarCenario(int id) throws BancoDeDadosException, Exception {
        Cenario cenario = cenarioRepository.findCenarioById(id);
        if (cenario == null) {
            throw new NaoEncontradoException("Cenário não encontrado");
        }
    }

}
