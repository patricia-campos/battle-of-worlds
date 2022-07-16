package br.com.dbc.trabalhofinalmodulo2.controller;

import br.com.dbc.trabalhofinalmodulo2.exceptions.BancoDeDadosException;

import br.com.dbc.trabalhofinalmodulo2.model.dto.ClassePersonagemDTO;
import br.com.dbc.trabalhofinalmodulo2.model.dto.JogadorDTO;
import br.com.dbc.trabalhofinalmodulo2.service.ClassePersonagemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public class ClassePersonagemController {

    @Autowired
    private ClassePersonagemService classePersonagemService;

    //=================================================================================================================
    @Operation(summary = "Lista as classes dos personagens", description = "Lista todos as classes cadastradas")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Classe de personagens listadas com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping
    public List<ClassePersonagemDTO> listar() throws BancoDeDadosException {
        return classePersonagemService.listarClassePersonagem();
    }

    //=================================================================================================================
}