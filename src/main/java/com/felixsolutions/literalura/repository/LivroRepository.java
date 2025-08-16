package com.felixsolutions.literalura.repository;

import com.felixsolutions.literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    boolean existsByGutendexId(Integer gutendexId);
    List<Livro> findByIdiomaIgnoreCase(String idioma);      // útil para listagem, se quiser manter
    long countByIdiomaIgnoreCase(String idioma);             // requerido pelo Trello (estatística por idioma)
}
