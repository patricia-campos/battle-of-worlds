package br.com.dbc.trabalhofinalmodulo2.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PersonagemCreateDTO {

    @NotNull
    private int idJogador;

    @NotEmpty
    @NotBlank
    @Schema(description = "Nome do personagem")
    private String nomePersonagem;

//    @Autowired
//    ClassePersonagemDTO classePersonagemDTO;
}
