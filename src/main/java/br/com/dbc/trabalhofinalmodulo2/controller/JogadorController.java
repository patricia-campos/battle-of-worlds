package br.com.dbc.trabalhofinalmodulo2.controller;

import br.com.dbc.trabalhofinalmodulo2.exceptions.BancoDeDadosException;
import br.com.dbc.trabalhofinalmodulo2.model.dto.JogadorCreateDTO;
import br.com.dbc.trabalhofinalmodulo2.model.dto.JogadorDTO;
import br.com.dbc.trabalhofinalmodulo2.service.JogadorService;
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

    @GetMapping
    public List<JogadorDTO> listar() throws BancoDeDadosException {
        return jogadorService.listarTodos();
    }

    @PostMapping
    public ResponseEntity<JogadorDTO> post(@Valid @RequestBody JogadorCreateDTO jogadorDTO) throws BancoDeDadosException {
        return ResponseEntity.ok(jogadorService.adicionar(jogadorDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<JogadorDTO> put(@PathVariable("id") Integer id,
                                          @Validated @RequestBody JogadorCreateDTO jogadorDTO) throws BancoDeDadosException {
        return ResponseEntity.ok(jogadorService.editar(jogadorDTO, id));
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) throws BancoDeDadosException {
        jogadorService.remover(jogadorService.findById(id));
    }
}
