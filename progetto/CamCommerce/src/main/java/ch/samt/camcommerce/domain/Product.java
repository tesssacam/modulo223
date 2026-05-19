package ch.samt.camcommerce.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product")
    @SequenceGenerator(name = "product", allocationSize = 1)
    private Long id;

    @NotBlank
    @Size(min = 1, max = 12, message = "Lunghezza consentita tra 1 e 12 caratteri")
    private String name;

    @NotBlank
    @Size(min = 5, max = 100, message = "Lunghezza consentita tra 5 e 100 caratteri")
    private String description;

    @NotNull
    private BigDecimal price;

    @NotNull
    private Integer quantity;


    private Boolean available;

    @NotBlank
    private String imageUrl;

    @ManyToOne
    private Category category;

    @OneToMany(mappedBy = "product")
    private Set<OrderItem> orderItems = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "productpromotionData.sql",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "promotion_id")
    )
    private Set<Promotion> promotions = new HashSet<>();
}
