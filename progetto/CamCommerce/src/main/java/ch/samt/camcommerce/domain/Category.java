package ch.samt.camcommerce.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq")
    @SequenceGenerator(name = "category_seq", allocationSize = 1)
    private Long id;

    @NotBlank
    @Size(min = 1, max = 12, message = "Lunghezza consentita tra 1 e 12 caratteri")
    private String name;

    @NotBlank
    @Size(min = 5, max = 100, message = "Lunghezza consentita tra 5 e 100 caratteri")
    private String description;

    @OneToMany(mappedBy = "category")
    private Set<Product> products;
}



