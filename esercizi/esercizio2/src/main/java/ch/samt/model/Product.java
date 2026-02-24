package ch.samt.model;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class Product {

    private Long id;

    @NotBlank(message = "il nome è obbligatorio")
    @Size(min = 2, max = 100, message = "deve essere tra 2 e 100 caratteri")
    private String name;

    @NotNull(message = "il prezzo è obbligatorio")
    @Positive(message = "il prezzo deve essere positivo")
    private Double price;

    @NotNull(message = "la data di scadenza è obbligatoria")
    @FutureOrPresent(message = "la data deve essere futura o di oggi")
    private LocalDate expirationDate;

    @NotBlank(message = "la descrizione è obbligatoria")
    @Size(min = 5, max = 255, message = "la descrizione deve avere almeno 5 caratteri")
    private String description;

    public Product() { // costruttore vuoto che serve a spring
    }

    public Product(Long id, String name, Double price, LocalDate expirationDate, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.expirationDate = expirationDate;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}