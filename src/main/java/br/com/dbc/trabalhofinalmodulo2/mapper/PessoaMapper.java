package br.com.dbc.trabalhofinalmodulo2.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PessoaMapper {

    @Autowired
    private ObjectMapper objectMapper;
//
//    public PessoaDTO toDTO(Pessoa pessoa) {
//        return objectMapper.convertValue(pessoa, PessoaDTO.class);
//    }
//
//    public Pessoa fromCreateDTO(PessoaCreateDTO pessoaCreateDTO) {
//        return objectMapper.convertValue(pessoaCreateDTO, Pessoa.class);
//    }
}
