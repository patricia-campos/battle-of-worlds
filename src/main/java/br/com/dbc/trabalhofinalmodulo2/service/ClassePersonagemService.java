package br.com.dbc.trabalhofinalmodulo2.service;

import br.com.dbc.trabalhofinalmodulo2.exceptions.BancoDeDadosException;
import br.com.dbc.trabalhofinalmodulo2.mapper.ClassePersonagemMapper;
import br.com.dbc.trabalhofinalmodulo2.mapper.PersonagemMapper;
import br.com.dbc.trabalhofinalmodulo2.dto.ClassePersonagemDTO;
import br.com.dbc.trabalhofinalmodulo2.dto.ClassePersonagemPostDTO;
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
        return classePersonagemRepository.listar().stream().map(classePersonagemMapper::toClassePersonagemDTO).toList();
    }

    public ClassePersonagemPostDTO retornaClassePorPersonagem(Integer idPersonagem) throws BancoDeDadosException, SQLException {
        return classePersonagemMapper.fromCreateDTOClasse(classePersonagemRepository.listarClassePorPersonagemID(idPersonagem));
    }
}

