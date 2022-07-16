package br.com.dbc.trabalhofinalmodulo2.service;

import br.com.dbc.trabalhofinalmodulo2.exceptions.BancoDeDadosException;
import br.com.dbc.trabalhofinalmodulo2.mapper.ClassePersonagemMapper;
import br.com.dbc.trabalhofinalmodulo2.model.dto.ClassePersonagemCreateDTO;
import br.com.dbc.trabalhofinalmodulo2.model.entities.ClassePersonagem;
import br.com.dbc.trabalhofinalmodulo2.repository.ClassePersonagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassePersonagemService {

    @Autowired
    ClassePersonagemRepository classePersonagemRepository;

    @Autowired
    ClassePersonagemMapper classePersonagemMapper;


    public ClassePersonagemCreateDTO adicionar(ClassePersonagemCreateDTO classePersonagem) throws BancoDeDadosException {
        return classePersonagemMapper
                .toClassePersonagemDTO(classePersonagemRepository
                        .adicionar(classePersonagemMapper.fromCreateDTO(classePersonagem)));
    }


    /*
    //TODO RETORNA CLASSE PERSONAGEM

    public void listarTodos() throws BancoDeDadosException {
        for (ClassePersonagem classe : classePersonagemRepository.listar()) {
            System.out.println(classe);
        }
    }
    */


    public ClassePersonagemCreateDTO editar(ClassePersonagemCreateDTO classePersonagem) throws BancoDeDadosException {
        return classePersonagemMapper
                .toClassePersonagemDTO(classePersonagemRepository
                        .editar(classePersonagemMapper.fromCreateDTO(classePersonagem).getIdClassePersonagem(),
                                classePersonagemMapper.fromCreateDTO(classePersonagem)));
    }


    public void remover(ClassePersonagem classePersonagem) throws BancoDeDadosException {
        if(classePersonagem == null){
            System.out.println("Classe inexistente");
        }else {
            classePersonagemRepository.remover(classePersonagem.getIdClassePersonagem());
        }
    }



    /*

    public ClassePersonagem retornaClasse(String nome) throws BancoDeDadosException {
        return classePersonagemRepository.listar()
                .stream()
                .filter(a -> Objects.equals(a.getNomeClassePersonagem(), nome))
                .map(a -> new ClassePersonagem(a.getIdClassePersonagem(), a.getNomeClassePersonagem(), a.getVidaClasse(), a.getDefesaClasse(), a.getAtaqueClasse(),a.getIdPersonagem()))
                .findFirst()
                .orElse(null);
    }
    public ClassePersonagem retornaClassePorPersonagem(Personagem personagem) throws BancoDeDadosException {
        return classePersonagemRepository.listar()
                .stream()
                .filter(a -> Objects.equals(a.getIdPersonagem(), personagem.getId()))
                .map(a -> new ClassePersonagem(a.getIdClassePersonagem(), a.getNomeClassePersonagem(), a.getVidaClasse(), a.getDefesaClasse(), a.getAtaqueClasse(),a.getIdPersonagem()))
                .findFirst()
                .orElse(null);
    }

    public void retornaClasseDoPersonagem(Personagem personagem) throws BancoDeDadosException {
        List<ClassePersonagem> listaPersonagemPorClasse = classePersonagemRepository.listar()
                .stream()
                .filter(a -> Objects.equals(a.getIdPersonagem(), personagem.getId()))
                .map(a -> new ClassePersonagem(a.getIdClassePersonagem(), a.getNomeClassePersonagem(), a.getVidaClasse(), a.getDefesaClasse(), a.getAtaqueClasse(),a.getIdPersonagem()))
                .collect(Collectors.toList());
        for (ClassePersonagem classe : listaPersonagemPorClasse) {
            System.out.println(classe);
        }
    }
*/
}

