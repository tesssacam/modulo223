package ch.samt.camcommerce.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "promotion_seq")
    @SequenceGenerator(name = "promotion_seq", allocationSize = 1)
    private Long id;

    @NotBlank
    private String name;

    private String description;

    private double discountPercent;

    private LocalDate startDate;

    private LocalDate endDate;

    @ManyToMany(mappedBy = "promotions")
    private Set<Product> products = new HashSet<>();

}
