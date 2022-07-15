package br.com.dbc.trabalhofinalmodulo2.controller;

import br.com.dbc.trabalhofinalmodulo2.exceptions.BancoDeDadosException;
import br.com.dbc.trabalhofinalmodulo2.model.dto.*;
import br.com.dbc.trabalhofinalmodulo2.service.PersonagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping ("/personagem")
public class PersonagemControler {

    @Autowired
    private PersonagemService personagemService;

    @GetMapping
    public List<PersonagemDTO> listar() throws BancoDeDadosException {
        return personagemService.listarTodos();
    }

    @PostMapping("/{idJogador}")
   public PersonagemDTO post(@PathVariable ("idJogador") Integer idJogador, @Valid @RequestBody PersonagemCreateDTO personagemCreateDTO) throws BancoDeDadosException {
       return personagemService.adicionar(personagemCreateDTO, idJogador);
   }

   @PutMapping("/{idPersonagem}")
    public PersonagemDTO put(@PathVariable ("idPersonagem") Integer idPersonagem, @Valid @RequestBody PersonagemPutDTO personagemDTO) throws BancoDeDadosException {
         return personagemService.editar(personagemDTO, idPersonagem);
    }

    @DeleteMapping("/{idPersonagem}")
    public void delete(@PathVariable ("idPersonagem") Integer idPersonagem) throws BancoDeDadosException {
        personagemService.remover(personagemService.listarPorId(idPersonagem));
    }
}
