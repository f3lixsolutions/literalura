package com.felixsolutions.literalura.model;

import jakarta.persistence.*;

@Entity
public class Livro {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer gutendexId;

    @Column(nullable=false)
    private String titulo;

    private String idioma;
    private Integer downloads;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Autor autor;

    public Livro() {}
    public Livro(Integer gid, String titulo, String idioma, Integer downloads, Autor autor) {
        this.gutendexId = gid; this.titulo = titulo; this.idioma = idioma; this.downloads = downloads; this.autor = autor;
    }

    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getIdioma() { return idioma; }
    public Integer getDownloads() { return downloads; }
    public Autor getAutor() { return autor; }

    @Override public String toString() {
        return "\"%s\" [%s] - %s (downloads: %s)".formatted(titulo, idioma, autor.getNome(), downloads);
    }
}
