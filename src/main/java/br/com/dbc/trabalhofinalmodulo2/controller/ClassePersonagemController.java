package br.com.dbc.trabalhofinalmodulo2.controller;

import br.com.dbc.trabalhofinalmodulo2.exceptions.BancoDeDadosException;
import br.com.dbc.trabalhofinalmodulo2.dto.ClassePersonagemDTO;
import br.com.dbc.trabalhofinalmodulo2.service.ClassePersonagemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;
@RestController
@RequestMapping("/classePersonagem")
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
    public List<ClassePersonagemDTO> listar() throws BancoDeDadosException, SQLException {
        return classePersonagemService.listarClassePersonagem();
    }

    //=================================================================================================================
}
