package ch.samt.customers.domain;

// JPA annotations
import jakarta.persistence.*;

// validazione campi
import jakarta.validation.constraints.*;

// validazione carta credito
import org.hibernate.validator.constraints.CreditCardNumber;

// Lombok getter/setter automatici
import lombok.Data;

// entity DB
@Data
@Entity
public class Customer {

    // ID primario
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq")
    @SequenceGenerator(name = "customer_seq", sequenceName = "customer_seq", allocationSize = 1)
    private Long id;

    // nome obbligatorio
    @NotBlank
    @Size(min = 2, message = "Lunghezza tra 2 e 10 caratteri")
    private String name;

    // cognome obbligatorio
    @NotBlank
    @Size(min = 2, message = "Lunghezza tra 2 e 10 caratteri")
    private String surname;

    // età obbligatoria tra 18 e 99
    @NotNull
    @Min(18)
    @Max(99)
    private Integer age;

    // città obbligatoria
    @NotBlank(message = "city è obbligatoria")
    @Size(min = 3, max = 20)
    private String city;

    // numero carta credito
    @NotBlank(message = "carta di credito obbligatoria")
    @CreditCardNumber(ignoreNonDigitCharacters = true)
    private String ccnumber;

    // scadenza carta formato MM/YY
    @NotBlank(message = "scadenza carta obbligatoria")
    @Pattern(regexp = "^(0[1-9]|1[0-2])/([0-9]{2})$")
    private String ccexpiration;

    // CVV 3 cifre
    @Digits(integer = 3, fraction = 0)
    private Integer cccvv;
}