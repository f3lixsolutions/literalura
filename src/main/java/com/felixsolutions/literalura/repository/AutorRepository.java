package com.felixsolutions.literalura.repository;

import com.felixsolutions.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNomeIgnoreCase(String nome);

    @Query("""
           SELECT a FROM Autor a
           WHERE (a.anoNascimento IS NULL OR a.anoNascimento <= :ano)
             AND (a.anoFalecimento IS NULL OR a.anoFalecimento >= :ano)
           """)
    List<Autor> encontrarVivosNoAno(Integer ano);
}
