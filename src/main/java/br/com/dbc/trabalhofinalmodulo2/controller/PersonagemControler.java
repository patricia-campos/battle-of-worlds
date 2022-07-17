package br.com.dbc.trabalhofinalmodulo2.controller;

import br.com.dbc.trabalhofinalmodulo2.exceptions.BancoDeDadosException;
import br.com.dbc.trabalhofinalmodulo2.exceptions.NaoEncontradoException;
import br.com.dbc.trabalhofinalmodulo2.model.dto.PersonagemClasseDTO;
import br.com.dbc.trabalhofinalmodulo2.model.dto.PersonagemCreateDTO;
import br.com.dbc.trabalhofinalmodulo2.model.dto.PersonagemDTO;
import br.com.dbc.trabalhofinalmodulo2.model.dto.PersonagemPutDTO;
import br.com.dbc.trabalhofinalmodulo2.service.PersonagemClasseService;
import br.com.dbc.trabalhofinalmodulo2.service.PersonagemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping ("/personagem")
public class PersonagemControler {

    @Autowired
    private PersonagemService personagemService;

    @Autowired
    private PersonagemClasseService personagemClasseService;

    @Operation(summary = "Lista todos personagens", description = "Lista todos personagens")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Personagens listados com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping
    public List<PersonagemDTO> listar() throws BancoDeDadosException, SQLException {
        return personagemService.listarTodos();
    }

    @Operation(summary = "Adiciona personagem", description = "Adiciona um personagem a um jogador")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Personagem adicionado com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping("/{idJogador}")
    public PersonagemDTO post(@PathVariable("idJogador") Integer idJogador,
                              @Valid @RequestBody PersonagemCreateDTO personagemCreateDTO
                             ) throws BancoDeDadosException, SQLException {
        return personagemService.adicionar(personagemCreateDTO, idJogador);
    }

    @Operation(summary = "Adiciona uma classe em um personagem", description = "Adiciona uma classe a um personagem existente")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Classe adicionada com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping("/novoPersonagemClasse")
    public PersonagemDTO criarPersonLigadoJogador(@Valid     @RequestBody PersonagemClasseDTO personagemClasseDTO) throws BancoDeDadosException, SQLException, NaoEncontradoException {
        return personagemClasseService.adicionarPersonagemComClasse(personagemClasseDTO);
    }

    @Operation(summary = "Edita um Personagem", description = "Edita um Personagem ja existente")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Personagem editado com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
   @PutMapping("/{idPersonagem}")
    public PersonagemDTO put(@PathVariable ("idPersonagem") Integer idPersonagem, @Valid @RequestBody PersonagemPutDTO personagemDTO) throws BancoDeDadosException, SQLException, NaoEncontradoException {
         return personagemService.editar(personagemDTO, idPersonagem);
    }

    @Operation(summary = "Deleta um Personagem", description = "Deleta um Personagem ja existente")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Personagem deletado com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @DeleteMapping("/{idPersonagem}")
    public void delete(@PathVariable("idPersonagem") Integer idPersonagem) throws BancoDeDadosException, SQLException, NaoEncontradoException {
        personagemService.remover(personagemService.listarPorId(idPersonagem));
    }
}
