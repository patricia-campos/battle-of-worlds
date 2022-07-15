package br.com.dbc.trabalhofinalmodulo2.service;

import br.com.dbc.trabalhofinalmodulo2.exceptions.BancoDeDadosException;
import br.com.dbc.trabalhofinalmodulo2.model.entities.ClassePersonagem;
import br.com.dbc.trabalhofinalmodulo2.model.entities.Personagem;
import br.com.dbc.trabalhofinalmodulo2.repository.ClassePersonagemRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public class ClassePersonagemService {

    ClassePersonagemRepository classePersonagemRepository = new ClassePersonagemRepository();

    public void adicionarClassePersonagem(Personagem personagem, ClassePersonagem classePersonagem) throws BancoDeDadosException {
        if(personagem == null){
            System.out.println("");
        }else if(classePersonagem == null){
            System.out.println("");
        }else{
            classePersonagemRepository.adicionar(classePersonagem, personagem.getId());
        }
    }

    public void listarTodos() throws BancoDeDadosException {
        for (ClassePersonagem classe : classePersonagemRepository.listar()) {
            System.out.println(classe);
        }
    }

    public void remover(ClassePersonagem classePersonagem) throws BancoDeDadosException {
        if(classePersonagem == null){
            System.out.println("Classe inexistente");
        }else {
            classePersonagemRepository.remover(classePersonagem.getIdClassePersonagem());
        }
    }

    public void editar(ClassePersonagem classePersonagem) throws BancoDeDadosException {
        if(classePersonagem == null){
            System.out.println("Classe inexistente");
        }else{
            classePersonagemRepository.editar(classePersonagem.getIdClassePersonagem(), classePersonagem);
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

