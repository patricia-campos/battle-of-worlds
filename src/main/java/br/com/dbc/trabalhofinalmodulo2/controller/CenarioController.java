package br.com.dbc.trabalhofinalmodulo2.controller;

import br.com.dbc.trabalhofinalmodulo2.exceptions.BancoDeDadosException;
import br.com.dbc.trabalhofinalmodulo2.model.dto.CenarioDTO;
import br.com.dbc.trabalhofinalmodulo2.service.CenarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public class CenarioController {

    @Autowired
    private CenarioService cenarioService;


    //=================================================================================================================

    @Operation(summary = "Listar os cenários",
            description = "Lista os cenários cadastrados")

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a lista com cenários cadastrados"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )

    @GetMapping("/listar")
    public List<CenarioDTO> list()throws BancoDeDadosException {
        return cenarioService.listar();
    }

    //=================================================================================================================
/*
    @Operation(summary = "Listar cenários por id",
            description = "Lista os cenários utilizando como parâmetro de busca o id do cenário")

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna o cenário do id informado"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/{idCenario}")
    public List<CenarioDTO> retornaCenario(@RequestParam int id) throws BancoDeDadosException {
        return cenarioService.buscarCenario(id);
    }

    //=================================================================================================================

    @Operation(summary = "Inserir novo contato em cliente cadastrado",
            description = "Insere novo contato no cadastro do cliente, utilizando id do cliente como parâmetro " +
                    "para este cadastro")

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Insere novo contato em cliente cadastrado"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping("/{idPessoa}") // localhost:8080/contato/idPessoa
    public ResponseEntity<ContatoDTO> create(@PathVariable("idPessoa") Integer id, @Valid @RequestBody ContatoCreateDTO contato)
            throws Exception {
        return ResponseEntity.ok(contatoService.create(id, contato));
    }
    //=================================================================================================================

    @Operation(summary = "Alterar dados de contato de cliente cadastrado",
            description = "Altera os dados de contato de cliente cadastrado no sistema, " +
                    "utilizando o id do contato como parâmetro para a alteração")

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Altera dados de contato de cliente"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )

    @PutMapping("/{idContato}") // (deve receber todos os dados) - localhost:8080/contato/idContato
    public ResponseEntity<ContatoDTO> update(@PathVariable("idContato") Integer id,
                                             @Valid @RequestBody ContatoCreateDTO contatoAtualizar) throws Exception {
        return ResponseEntity.ok(contatoService.update(id, contatoAtualizar));
    }

    //=================================================================================================================

    @Operation(summary = "Excluir contato de cliente cadastrado",
            description = "Exclui contato de cliente cadastrado no sistema, utilizando o id do contato como " +
                    "parâmetro para a exclusão")

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Exclui contato cadastrado"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @DeleteMapping("/{idContato}") // Excluir contato - localhost:8080/contato/idContato
    public void delete(@PathVariable("idContato") Integer id) throws Exception {
        contatoService.delete(id);
    }
    //=================================================================================================================
}
*/

}
