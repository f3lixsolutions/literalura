package com.felixsolutions.literalura.service;

import org.springframework.stereotype.Service;

@Service
public class CatalogoService {

    public void buscarLivroPorTitulo(String titulo) {
        System.out.println("(em breve) buscar e salvar: " + titulo);
    }

    public void listarLivros() {
        System.out.println("(em breve) listar livros");
    }

    public void listarAutores() {
        System.out.println("(em breve) listar autores");
    }

    public void listarAutoresVivosNoAno(int ano) {
        System.out.println("(em breve) autores vivos em " + ano);
    }

    public void listarLivrosPorIdioma(String idioma) {
        System.out.println("(em breve) livros por idioma: " + idioma);
    }
}
