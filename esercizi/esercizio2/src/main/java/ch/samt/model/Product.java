package ch.samt.model;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

/*
 Questa classe rappresenta un prodotto del nostro sistema
 È un semplice oggetto Java (POJO) che contiene solo dati
 Le annotazioni sopra i campi servono per attivare la validazione automatica
*/
public class Product {

    // ID del prodotto
    // Non mettiamo validazione perché controlliamo manualmente nel controller
    private Long id;

    /*
     Il nome non può essere vuoto
     @NotBlank controlla che non sia null e nemmeno stringa vuota
     @Size controlla che la lunghezza sia compresa tra 2 e 100 caratteri
    */
    @NotBlank(message = "il nome è obbligatorio")
    @Size(min = 2, max = 100, message = "deve essere tra 2 e 100 caratteri")
    private String name;

    /*
     Il prezzo non può essere null
     Deve essere positivo
    */
    @NotNull(message = "il prezzo è obbligatorio")
    @Positive(message = "il prezzo deve essere positivo")
    private Double price;

    /*
     La data di scadenza non può essere null
     Deve essere oggi o nel futuro
    */
    @NotNull(message = "la data di scadenza è obbligatoria")
    @FutureOrPresent(message = "la data deve essere futura o di oggi")
    private LocalDate expirationDate;

    /*
     La descrizione non può essere vuota
     Deve avere almeno 5 caratteri
    */
    @NotBlank(message = "la descrizione è obbligatoria")
    @Size(min = 5, max = 255, message = "la descrizione deve avere almeno 5 caratteri")
    private String description;

    // Costruttore vuoto obbligatorio per Spring quando crea l'oggetto dal form
    public Product() {
    }

    // Costruttore completo usato nel blocco statico per creare prodotti iniziali
    public Product(Long id, String name, Double price, LocalDate expirationDate, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.expirationDate = expirationDate;
        this.description = description;
    }

    // Getter e setter standard
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public LocalDate getExpirationDate() { return expirationDate; }
    public void setExpirationDate(LocalDate expirationDate) { this.expirationDate = expirationDate; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}