package ch.samt.camcommerce.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_item_seq")
    @SequenceGenerator(name = "order_item_seq", allocationSize = 1)
    private Long id;

    @NotNull
    private Integer quantity;

    @NotNull
    private BigDecimal price;

    @ManyToOne
    private Order order;

    @ManyToOne
    private Product product;
}
