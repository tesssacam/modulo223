package ch.samt.customers.domain;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq")
    @SequenceGenerator(name = "customer_seq", allocationSize = 1)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 30, message = "Lunghezza consentita tra 3 e 30 caratteri")
    private String name;

    @NotBlank
    @Size(min = 3, max = 30, message = "Lunghezza consentita tra 3 e 30 caratteri")
    private String surname;

    @NotNull
    @Min(18)
    @Max(99)
    private Integer age;


    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    @Valid
    @Size(min = 3, max = 40, message = "Lunghezza consentita tra 3 e 40 caratteri")
    private Address address;

    //esclude il ToString delle reservation per evitare il loop dei
    //To String che si invocano a vicenda e generano dipendenza circolare
    @ToString.Exclude
    @OneToMany (mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Reservation> reservations;

}
