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
        var opcao = -1;
        while (opcao != 0) {
            System.out.println("\n=== LiterAlura ===");
            System.out.println("1) Buscar livro pelo título (API -> salvar)");
            System.out.println("2) Listar livros registrados");
            System.out.println("3) Listar autores registrados");
            System.out.println("4) Listar autores vivos em determinado ano");
            System.out.println("5) Quantidade de livros por idioma [pt|en|es|fr]");
            System.out.println("0) Sair");
            System.out.print("Escolha: ");
            var linha = in.nextLine().trim();
            if (linha.isEmpty()) continue;
            try { opcao = Integer.parseInt(linha); } catch (Exception e) { opcao = -1; }

            switch (opcao) {
                case 1 -> {
                    System.out.print("Título: ");
                    service.buscarLivroPorTitulo(in.nextLine().trim());
                }
                case 2 -> service.listarLivros();
                case 3 -> service.listarAutores();
                case 4 -> {
                    System.out.print("Ano: ");
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
                case 0 -> System.out.println("Até mais!");
                default -> System.out.println("Opção inválida.");
            }
        }
    }
}
