package br.com.dbc.trabalhofinalmodulo2.service;

import br.com.dbc.trabalhofinalmodulo2.exceptions.BancoDeDadosException;
import br.com.dbc.trabalhofinalmodulo2.mapper.CenarioMapper;
import br.com.dbc.trabalhofinalmodulo2.mapper.ClassePersonagemMapper;
import br.com.dbc.trabalhofinalmodulo2.mapper.PersonagemMapper;

import br.com.dbc.trabalhofinalmodulo2.model.dto.ClassePersonagemDTO;

import br.com.dbc.trabalhofinalmodulo2.model.entities.ClassePersonagem;
import br.com.dbc.trabalhofinalmodulo2.repository.ClassePersonagemRepository;
import br.com.dbc.trabalhofinalmodulo2.repository.PersonagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ClassePersonagemService {

    @Autowired
    ClassePersonagemRepository classePersonagemRepository;

    @Autowired
    ClassePersonagemMapper classePersonagemMapper;

    @Autowired
    private PersonagemRepository personagemRepository;

    @Autowired
    private PersonagemMapper personagemMapper;


    public List<ClassePersonagemDTO> listarClassePersonagem() throws BancoDeDadosException, SQLException {
        List<ClassePersonagem> listClasse = classePersonagemRepository.listar();
        List<ClassePersonagemDTO> listaDTO = listClasse.stream().map(classePersonagemMapper::toClassePersonagemDTO).toList();
        listaDTO.forEach(p -> {
            listClasse.stream().filter(p1 -> p1.getIdClassePersonagem() == p.getIdClassePersonagem()).forEach(p2 -> p.setTipoClassePersonagem(p2.getTipoPersonagem()));
        });
        return listaDTO;
    }

    public ClassePersonagemPostDTO retornaClassePorPersonagem(Integer idPersonagem) throws BancoDeDadosException, SQLException {
        return classePersonagemMapper.fromCreateDTOClasse(classePersonagemRepository.listarClassePorPersonagemID(idPersonagem));
    }
}

