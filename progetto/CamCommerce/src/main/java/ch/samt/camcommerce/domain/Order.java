package ch.samt.camcommerce.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
    @SequenceGenerator(name = "order_seq", allocationSize = 1)
    private Long id;

    @NotBlank
    private LocalDateTime orderDate;

    @NotNull
    private double total;

    @NotBlank
    @Size(min = 1, max = 20, message = "Lunghezza consentita tra 1 e 20 caratteri")
    private String status;

    @OneToMany(mappedBy = "order")
    private Set<OrderItem> items = new HashSet<>();
}
