package com.felixsolutions.literalura.cli;

import com.felixsolutions.literalura.service.CatalogoService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Menu {

    private final CatalogoService service;
    private final Scanner in = new Scanner(System.in);

    public Menu(CatalogoService service) {
        this.service = service;
    }

    public void start() {
        int op = -1;
        while (op != 0) {
            System.out.println("""
                \n=== LiterAlura ===
                1) Buscar livro pelo título (API -> salvar)
                2) Listar livros registrados
                3) Listar autores registrados
                4) Listar autores vivos em determinado ano
                5) Listar livros por idioma [pt|en|es|fr]
                0) Sair
                Escolha: """);

            String entrada = in.nextLine().trim();
            try { op = Integer.parseInt(entrada); } catch (Exception e) { op = -1; }

            switch (op) {
                case 1 -> {
                    System.out.print("Título: ");
                    service.buscarLivroPorTitulo(in.nextLine().trim());
                }
                case 2 -> service.listarLivros();
                case 3 -> service.listarAutores();
                case 4 -> {
                    System.out.print("Ano (yyyy): ");
                    try {
                        int ano = Integer.parseInt(in.nextLine().trim());
                        service.listarAutoresVivosNoAno(ano);
                    } catch (NumberFormatException e) {
                        System.out.println("Ano inválido.");
                    }
                }
                case 5 -> {
                    System.out.print("Idioma [pt|en|es|fr]: ");
                    service.listarLivrosPorIdioma(in.nextLine().trim());
                }
                case 0 -> System.out.println("Até mais! 👋");
                default -> System.out.println("Opção inválida.");
            }
        }
    }
}
