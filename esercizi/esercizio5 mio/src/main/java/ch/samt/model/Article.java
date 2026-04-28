package ch.samt.model;

import jakarta.validation.constraints.*;

// Classe modello per gli articoli della biblioteca
public class Article {

    private Long id; // ID unico articolo

    @NotBlank(message = "Il titolo è obbligatorio")
    @Size(min = 2, max = 100, message = "Il titolo deve avere tra 2 e 100 caratteri")
    private String title; // titolo articolo

    @NotBlank(message = "L'autore è obbligatorio")
    @Size(min = 2, max = 50, message = "Nome autore tra 2 e 50 caratteri")
    private String author; // autore articolo

    @NotNull(message = "Numero pagine obbligatorio")
    @Positive(message = "Le pagine devono essere > 0")
    private Integer pages; // numero pagine

    private boolean deleted; // indica se articolo eliminato

    // Costruttore vuoto per Spring
    public Article() { }

    // Costruttore completo
    public Article(Long id, String title, String author, Integer pages, boolean deleted) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.deleted = deleted;
    }

    // GETTERS e SETTERS
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public Integer getPages() { return pages; }
    public void setPages(Integer pages) { this.pages = pages; }

    public boolean isDeleted() { return deleted; }
    public void setDeleted(boolean deleted) { this.deleted = deleted; }
}