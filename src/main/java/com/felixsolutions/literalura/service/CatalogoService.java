package com.felixsolutions.literalura.service;

import com.felixsolutions.literalura.dto.GutendexAuthorDTO;
import com.felixsolutions.literalura.dto.GutendexBookDTO;
import com.felixsolutions.literalura.model.Autor;
import com.felixsolutions.literalura.model.Livro;
import com.felixsolutions.literalura.repository.AutorRepository;
import com.felixsolutions.literalura.repository.LivroRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
public class CatalogoService {

    private final GutendexClient client;
    private final AutorRepository autorRepo;
    private final LivroRepository livroRepo;

    public CatalogoService(GutendexClient client, AutorRepository autorRepo, LivroRepository livroRepo) {
        this.client = client;
        this.autorRepo = autorRepo;
        this.livroRepo = livroRepo;
    }

    public void buscarLivroPorTitulo(String titulo) {
        List<GutendexBookDTO> resultados = client.buscarPorTitulo(titulo);
        if (resultados.isEmpty()) {
            System.out.println("Nenhum resultado para: " + titulo);
            return;
        }

        // escolhe o mais baixado entre os resultados
        GutendexBookDTO book = resultados.stream()
                .max(Comparator.comparing(g -> g.getDownloadCount() == null ? 0 : g.getDownloadCount()))
                .orElse(resultados.get(0));

        if (livroRepo.existsByGutendexId(book.getId())) {
            System.out.println("Livro já cadastrado no banco.");
            return;
        }

        // regra do desafio: considerar apenas o primeiro autor e o primeiro idioma
        GutendexAuthorDTO a = (book.getAuthors() == null || book.getAuthors().isEmpty())
                ? null : book.getAuthors().get(0);
        String idioma = (book.getLanguages() == null || book.getLanguages().isEmpty())
                ? "nd" : book.getLanguages().get(0);

        Autor autor = (a == null)
                ? autorRepo.findByNomeIgnoreCase("Desconhecido")
                .orElseGet(() -> autorRepo.save(new Autor("Desconhecido", null, null)))
                : autorRepo.findByNomeIgnoreCase(a.getName())
                .orElseGet(() -> autorRepo.save(new Autor(a.getName(), a.getBirthYear(), a.getDeathYear())));

        Livro livro = new Livro(book.getId(), book.getTitle(), idioma, book.getDownloadCount(), autor);
        livroRepo.save(livro);

        System.out.println("Salvo: " + livro);
    }

    // >>> CORREÇÃO: transação só para garantir a inicialização do autor (LAZY)
    @Transactional(readOnly = true)
    public void listarLivros() {
        var livros = livroRepo.findAll();
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
            return;
        }
        // aqui podemos usar o toString do Livro com segurança (autor será inicializado)
        livros.forEach(System.out::println);
    }

    public void listarAutores() {
        var autores = autorRepo.findAll();
        if (autores.isEmpty()) {
            System.out.println("Nenhum autor cadastrado.");
            return;
        }
        autores.forEach(System.out::println);
    }

    public void listarAutoresVivosNoAno(int ano) {
        var vivos = autorRepo.encontrarVivosNoAno(ano);
        if (vivos.isEmpty()) {
            System.out.println("Nenhum autor encontrado vivo em " + ano);
            return;
        }
        vivos.forEach(System.out::println);
    }

    // Trello: estatística de quantidade por idioma
    public void listarLivrosPorIdioma(String idioma) {
        long qtd = livroRepo.countByIdiomaIgnoreCase(idioma);
        System.out.println("Quantidade de livros no idioma '" + idioma + "': " + qtd);
    }
}
