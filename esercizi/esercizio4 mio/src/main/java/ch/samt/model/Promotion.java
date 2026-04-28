package ch.samt.model;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class Promotion {

    private Long id;

    @NotBlank(message = "Il nome del prodotto è obbligatorio")
    private String productName;

    @NotNull(message = "Il prezzo originale è obbligatorio")
    @Positive(message = "Il prezzo originale deve essere positivo")
    private Double originalPrice;

    @NotNull(message = "Lo sconto è obbligatorio")
    @Positive(message = "Lo sconto deve essere positivo")
    @Max(value = 100, message = "Lo sconto non può superare il 100%")
    private Double discountPercent;

    @NotNull(message = "La data di inizio è obbligatoria")
    @FutureOrPresent(message = "La data di inizio deve essere oggi o futura")
    private LocalDate startDate;

    @NotNull(message = "La data di fine è obbligatoria")
    @FutureOrPresent(message = "La data di fine deve essere oggi o futura")
    private LocalDate endDate;

    public Promotion() {}

    public Promotion(Long id, String productName, Double originalPrice, Double discountPercent,
                     LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.productName = productName;
        this.originalPrice = originalPrice;
        this.discountPercent = discountPercent;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // getter e setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public Double getOriginalPrice() { return originalPrice; }
    public void setOriginalPrice(Double originalPrice) { this.originalPrice = originalPrice; }
    public Double getDiscountPercent() { return discountPercent; }
    public void setDiscountPercent(Double discountPercent) { this.discountPercent = discountPercent; }
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    // calcolo prezzo scontato
    public Double getDiscountedPrice() {
        return originalPrice * (1 - discountPercent / 100);
    }
}