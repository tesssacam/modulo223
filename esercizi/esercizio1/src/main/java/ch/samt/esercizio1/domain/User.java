package ch.samt.esercizio1.domain;

// Lombok crea automaticamente:
// getter, setter, costruttori, toString
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor // Costruttore con parametri
@NoArgsConstructor  // Costruttore vuoto
@Data               // Getter + Setter automatici
public class User {

    private Long id;
    private String name;
    private String surname;
}