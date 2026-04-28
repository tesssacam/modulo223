package ch.samt.gardenwarehouse.service; // package service

import ch.samt.gardenwarehouse.data.ItemRepository; // repository (DB)
import ch.samt.gardenwarehouse.domain.Item; // entity
import org.springframework.beans.factory.annotation.Autowired; // injection
import org.springframework.stereotype.Service; // indica service

import java.util.List; // lista
import java.util.Optional; // valore opzionale

@Service // classe di logica applicativa
public class ItemService {

    private final ItemRepository itemRepository; // repository per DB

    @Autowired // Spring lo crea automaticamente
    public ItemService(ItemRepository itemRepository) { // costruttore
        this.itemRepository = itemRepository; // assegno repository
    }

    // Restituisce tutti gli item (punto 4)
    public List<Item> getAllItems() { // metodo che ritorna lista
        return itemRepository.findAll(); // prende tutti dal DB
    }

    // Trova item per codice (punto 5)
    public Optional<Item> findByCode(String code) { // cerca per codice
        return itemRepository.findByCode(code); // query al DB
    }

    // Salva item
    public void save(Item item) { // metodo per salvare
        itemRepository.save(item); // salva nel DB
    }

    // Elimina item (non richiesto ma utile)
    public void deleteById(Long id) { // elimina per ID
        itemRepository.deleteById(id); // cancella dal DB
    }

    // Vendita item (punto 6)
    public boolean sellItem(String code) { // metodo vendita

        Optional<Item> itemOpt = findByCode(code);
        // cerco item

        if (itemOpt.isPresent() && itemOpt.get().getItemCount() > 0) {
            // controllo: esiste e quantità > 0

            Item item = itemOpt.get();
            // prendo l'item

            item.setItemCount(item.getItemCount() - 1);
            // diminuisco quantità

            save(item);
            // salvo nel DB

            return true; // successo
        }

        return false; // fallito
    }

    // Aggiunta quantità (punto 7)
    public boolean addItemCount(String code, int number) {
        // metodo per aggiungere quantità

        Optional<Item> itemOpt = findByCode(code);
        // cerco item

        if (itemOpt.isPresent() && number > 0) {
            // controllo: esiste e numero valido

            Item item = itemOpt.get();
            // prendo item

            item.setItemCount(item.getItemCount() + number);
            // aumento quantità

            save(item);
            // salvo nel DB

            return true; // successo
        }

        return false; // errore
    }
}