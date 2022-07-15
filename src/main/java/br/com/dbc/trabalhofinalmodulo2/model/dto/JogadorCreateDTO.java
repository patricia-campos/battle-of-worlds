package br.com.dbc.trabalhofinalmodulo2.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JogadorCreateDTO {


    @NotBlank(message = "O nome do jogador é obrigatório")
    @Schema(description = "Nome do jogador")
    private String nomeJogador;


    @NotBlank(message = "Senha é obrigatória")
    @Schema(description = "Senha do jogador")
    private String senha;


    @NotBlank(message = "Email do jogador é obrigatório")
    @Schema(description = "Email do jogador")
    private String email;
}
