package br.com.dbc.trabalhofinalmodulo2.service;

import br.com.dbc.trabalhofinalmodulo2.exceptions.BancoDeDadosException;
import br.com.dbc.trabalhofinalmodulo2.mapper.ClassePersonagemMapper;
import br.com.dbc.trabalhofinalmodulo2.mapper.PersonagemMapper;
import br.com.dbc.trabalhofinalmodulo2.model.dto.ClassePersonagemCreateDTO;
import br.com.dbc.trabalhofinalmodulo2.model.dto.ClassePersonagemDTO;
import br.com.dbc.trabalhofinalmodulo2.model.dto.JogadorDTO;
import br.com.dbc.trabalhofinalmodulo2.model.dto.PersonagemDTO;
import br.com.dbc.trabalhofinalmodulo2.model.entities.ClassePersonagem;
import br.com.dbc.trabalhofinalmodulo2.model.entities.Personagem;
import br.com.dbc.trabalhofinalmodulo2.repository.ClassePersonagemRepository;
import br.com.dbc.trabalhofinalmodulo2.repository.PersonagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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


    public List<ClassePersonagemDTO> listarClassePersonagem() throws BancoDeDadosException {
        return classePersonagemRepository.listar().stream().map(classePersonagemMapper::toClassePersonagemDTO).toList();
    }


}

