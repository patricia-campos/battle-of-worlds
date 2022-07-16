package br.com.dbc.trabalhofinalmodulo2.controller;

import br.com.dbc.trabalhofinalmodulo2.exceptions.BancoDeDadosException;
import br.com.dbc.trabalhofinalmodulo2.model.dto.CenarioCreateDTO;
import br.com.dbc.trabalhofinalmodulo2.model.dto.CenarioDTO;
import br.com.dbc.trabalhofinalmodulo2.service.CenarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;


@RestController
@RequestMapping("/cenario")
public class CenarioController {

    @Autowired
    private CenarioService cenarioService;


    //=================================================================================================================

    @Operation(summary = "Listar os cenários",
               description = "Lista os cenários cadastrados")

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Cenários retornados com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )

    @GetMapping("/listar")
    public List<CenarioDTO> list() throws BancoDeDadosException, SQLException {
        return cenarioService.listar();
    }

    //=================================================================================================================

    @Operation(summary = "Inserir novo cenário",
               description = "Insere novo cenário")

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Novo cenário inserido com sucesso!"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping
    public ResponseEntity<CenarioDTO> post(@Valid @RequestBody CenarioDTO cenarioDTO) throws BancoDeDadosException, SQLException {

        return ResponseEntity.ok(cenarioService.adicionar(cenarioDTO));
    }

    //=================================================================================================================

    //todo checar o id

    @Operation(summary = "Alterar dados de cenário cadastrado",
            description = "Altera os dados de cenário cadastrado")

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Cenário alterado com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )

    @PutMapping("/{idCenario}") // (deve receber todos os dados)
    public ResponseEntity<CenarioDTO> update(@PathVariable("id") Integer id,
                                             @Valid @RequestBody CenarioCreateDTO cenarioDTO) throws BancoDeDadosException, SQLException {
        return ResponseEntity.ok(cenarioService.editar(cenarioDTO));
    }

    //=================================================================================================================

    @Operation(summary = "Excluir cenário",
            description = "Exclui cenário existente")

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Cenário excluído com sucesso!"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @DeleteMapping("/{idCenario}")
    public void remover(@PathVariable("idCenario") Integer id) throws BancoDeDadosException, SQLException {
        cenarioService.remover(id);
    }
    //=================================================================================================================
}



