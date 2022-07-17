package br.com.dbc.trabalhofinalmodulo2.controller;

import br.com.dbc.trabalhofinalmodulo2.exceptions.BancoDeDadosException;
import br.com.dbc.trabalhofinalmodulo2.exceptions.BossNaoEncontradoException;
import br.com.dbc.trabalhofinalmodulo2.exceptions.VidaMenorQueZero;
import br.com.dbc.trabalhofinalmodulo2.model.dto.BatalhaCreateDTO;
import br.com.dbc.trabalhofinalmodulo2.model.dto.BatalhaDTO;
import br.com.dbc.trabalhofinalmodulo2.service.BatalhaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

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
    @PostMapping("/iniciar")
    public BatalhaDTO iniciar(@RequestBody BatalhaCreateDTO batalhaCreateDTO) throws BancoDeDadosException, Exception {
        return batalhaService.adicionar(batalhaCreateDTO);
    }

    @Operation(summary = "Ataca o boss", description = "Jogador faz um ataque no boss")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Sucesso no ataque"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/atacar")
    public String atacar(@RequestParam int idBatalha) throws BancoDeDadosException, Exception {
        return batalhaService.atacar(idBatalha);
    }

    @Operation(summary = "Defender ataque", description = "Jogador faz uma defesa e toma menos dano")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Sucesso na defesa"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/defender")
    public String defender(@RequestParam int idBatalha) throws BancoDeDadosException, Exception {
        return batalhaService.defender(idBatalha);
    }

    @Operation(summary = "Fugir da batalha", description = "Jogador foge da batalha")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Sucesso na fuga"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/fugir")
    public String fugir(@RequestParam int idBatalha) throws BancoDeDadosException, BossNaoEncontradoException, SQLException {
        return batalhaService.fugir(idBatalha);
    }

}
