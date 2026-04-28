package ch.samt.gardenwarehouse.data;

import ch.samt.gardenwarehouse.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository // indica accesso al DB
public interface ItemRepository extends JpaRepository<Item, Long> {

    // Metodo custom: cerca item tramite codice
    Optional<Item> findByCode(String code);
}