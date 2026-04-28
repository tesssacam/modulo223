package ch.samt.customers.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

@Data
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq")
    @SequenceGenerator(name = "customer_seq", sequenceName = "customer_seq", allocationSize = 1)
    private Long id;

    @NotBlank
    @Size(min = 2, message = "Lunghezza tra 2 e 10 caratteri")
    private String name;

    @NotBlank
    @Size(min = 2, message = "Lunghezza tra 2 e 10 caratteri")
    private String surname;

    @NotNull
    @Min(18)
    @Max(99)
    private Integer age;

    @NotBlank(message = "city è obbligatoria")
    @Size(min = 3, max = 20, message = "lunghezza tra 3 e 20 caratteri")
    private String city;

    @NotBlank(message = "o carta di credito obbligatorio")
    @CreditCardNumber(ignoreNonDigitCharacters = true) // puoi disabilitarlo se vuoi testare senza un numero reale
    private String ccnumber;

    @NotBlank(message = "scadenza carta obbligatoria")
    @Pattern(regexp = "^(0[1-9]|1[0-2])/([0-9]{2})$", message = "Formato scadenza MM/YY")
    private String ccexpiration;


    @Digits(integer = 3, fraction = 0, message = "cvv deve essere composto da 3 cifre")
    private Integer cccvv;
}
