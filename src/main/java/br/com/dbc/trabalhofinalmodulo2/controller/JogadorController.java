package br.com.dbc.trabalhofinalmodulo2.controller;

import br.com.dbc.trabalhofinalmodulo2.exceptions.BancoDeDadosException;
import br.com.dbc.trabalhofinalmodulo2.model.dto.JogadorCreateDTO;
import br.com.dbc.trabalhofinalmodulo2.model.dto.JogadorDTO;
import br.com.dbc.trabalhofinalmodulo2.service.JogadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/jogador")
@Validated
public class JogadorController {

    @Autowired
    private JogadorService jogadorService;

    @Operation(summary = "Lista todos jogadores", description = "Lista todos jogadores")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Jogadores listados com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping
    public List<JogadorDTO> listar() throws BancoDeDadosException {
        return jogadorService.listarTodos();
    }

    @Operation(summary = "Retornar um jogador", description = "Retorna um jogador pelo seu id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Jogador retornado com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<JogadorDTO> buscar(@PathVariable Integer id) throws BancoDeDadosException {
        return ResponseEntity.ok(jogadorService.listarPorId(id));
    }

    @Operation(summary = "Cria um jogador", description = "Cria um novo jogador")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Jogador criado com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping
    public ResponseEntity<JogadorDTO> post(@Valid @RequestBody JogadorCreateDTO jogadorDTO) throws BancoDeDadosException {
        return ResponseEntity.ok(jogadorService.adicionar(jogadorDTO));
    }

    @Operation(summary = "Edita um jogador", description = "Edita um jogador ja existente")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Jogador editado com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<JogadorDTO> put(@PathVariable("id") Integer id,
                                          @Validated @RequestBody JogadorCreateDTO jogadorDTO) throws BancoDeDadosException {
        return ResponseEntity.ok(jogadorService.editar(jogadorDTO, id));
    }

    @Operation(summary = "Deleta um jogador", description = "Deleta um jogador ja existente")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Jogador deletado com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) throws BancoDeDadosException {
        jogadorService.remover(jogadorService.listarPorId(id));
    }
}
