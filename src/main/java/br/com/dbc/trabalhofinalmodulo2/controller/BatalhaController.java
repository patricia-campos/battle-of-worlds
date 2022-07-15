package br.com.dbc.trabalhofinalmodulo2.controller;


import br.com.dbc.trabalhofinalmodulo2.exceptions.BancoDeDadosException;
import br.com.dbc.trabalhofinalmodulo2.model.dto.BatalhaCreateDTO;
import br.com.dbc.trabalhofinalmodulo2.model.dto.BatalhaDTO;
import br.com.dbc.trabalhofinalmodulo2.service.BatalhaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/batalha")
public class BatalhaController {

    @Autowired
    private BatalhaService batalhaService;

    @Operation(summary = "Inicia Batalha", description = "Inicia uma batalha")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Batalha iniciada com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping("/inicia")
    public BatalhaDTO create(@RequestBody BatalhaCreateDTO batalhaCreateDTO) throws BancoDeDadosException {
        return batalhaService.adicionar(batalhaCreateDTO);
    }



}
