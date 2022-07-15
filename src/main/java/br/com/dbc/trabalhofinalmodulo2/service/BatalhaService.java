package br.com.dbc.trabalhofinalmodulo2.service;

import br.com.dbc.trabalhofinalmodulo2.exceptions.BancoDeDadosException;
import br.com.dbc.trabalhofinalmodulo2.mapper.BatalhaMapper;
import br.com.dbc.trabalhofinalmodulo2.model.dto.BatalhaCreateDTO;
import br.com.dbc.trabalhofinalmodulo2.model.dto.BatalhaDTO;
import br.com.dbc.trabalhofinalmodulo2.model.entities.Batalha;
import br.com.dbc.trabalhofinalmodulo2.repository.BatalhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BatalhaService {

    @Autowired
    BatalhaRepository batalhaRepository;

    @Autowired
    BatalhaMapper batalhaMapper;

    public BatalhaDTO adicionar(BatalhaCreateDTO batalha) throws BancoDeDadosException {
        Batalha batalha1 = batalhaMapper.fromCreateDTO(batalha);
            return batalhaMapper.toBatalhaDTO(batalhaRepository.adicionar(batalha1));
    }

    public List<BatalhaCreateDTO> listar() throws BancoDeDadosException {
        List<Batalha> batalhaList = batalhaRepository.listar();
        return batalhaList.stream()
                .map(a -> batalhaMapper.toCreateDTO(a))
                .collect(Collectors.toList());
    }

    //No momento do projeto não está implementado
    public void remover(Batalha batalha) throws BancoDeDadosException {
            batalhaRepository.remover(batalha.getIdBatalha());
    }

    //No momento do projeto não está implementado
    public void editar(Integer id, Batalha batalha) throws BancoDeDadosException {
            batalhaRepository.editar(id, batalha);
    }



}
