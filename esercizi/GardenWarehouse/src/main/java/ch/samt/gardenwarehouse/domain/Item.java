package ch.samt.gardenwarehouse.domain;

import jakarta.persistence.*; // JPA
import jakarta.validation.constraints.*; // Validazioni
import lombok.Data; // Getter e setter automatici

@Data // genera getter, setter, toString
@Entity // questa classe diventa una tabella nel DB
public class Item {

    @Id // chiave primaria
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_seq")
    @SequenceGenerator(name = "item_seq", sequenceName = "item_seq", allocationSize = 1)
    private Long id; // ID automatico

    @NotBlank(message = "il codice è obbligatorio") // non può essere vuoto
    @Pattern(regexp = "^[a-zA-Z]{3}-\\d{2}$", message = "formato abc-12") // formato corretto
    private String code;

    @NotBlank(message = "tipo obbligatorio")
    private String type;

    @NotBlank(message = "nome obbligatorio")
    private String name;

    @NotNull(message = "prezzo obbligatorio")
    @Positive(message = "prezzo > 0")
    private Double price;

    @NotNull(message = "quantità obbligatoria")
    @Min(value = 0, message = "non può essere negativa")
    private Integer itemCount;
}