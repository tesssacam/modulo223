package ch.samt.gardenwarehouse.controller; // package: cartella logica del controller

import ch.samt.gardenwarehouse.domain.Item; // import della classe Item (entity)
import ch.samt.gardenwarehouse.service.ItemService; // import del service per la logica
import jakarta.validation.Valid; // serve per attivare le validazioni
import org.springframework.beans.factory.annotation.Autowired; // per injection automatica
import org.springframework.stereotype.Controller; // indica che è un controller web
import org.springframework.ui.Model; // serve per passare dati alla pagina HTML
import org.springframework.validation.Errors; // contiene errori di validazione
import org.springframework.web.bind.annotation.*; // contiene tutte le annotazioni web

import java.util.Optional; // contenitore che può avere o no un valore

@Controller // questa classe gestisce le richieste HTTP (web)
public class ItemController {

    private final ItemService itemService; // variabile per usare il service

    @Autowired // Spring inserisce automaticamente il service
    public ItemController(ItemService itemService) { // costruttore
        this.itemService = itemService; // assegno il service alla variabile
    }

    // LISTA (punto 4)
    @GetMapping("/items") // quando si va su /items
    public String loadItems(Model model) { // metodo che ritorna il nome della pagina

        model.addAttribute("items", itemService.getAllItems());
        // metto nel model la lista degli item presa dal service

        return "itemList"; // apre la pagina itemList.html
    }

    // DETTAGLIO (punto 5)
    @GetMapping("/items/{code}") //http://localhost:8080/items/<code>
    public String loadItemDetail(Model model, @PathVariable String code) {
        // @PathVariable prende il valore dall'URL

        Optional<Item> item = itemService.findByCode(code);
        // cerco l'item tramite codice

        if (item.isPresent()) { // se esiste
            model.addAttribute("item", item.get());
            // metto l'item nel model

            return "itemDetail"; // apro pagina dettaglio
        }

        model.addAttribute("error", "Item non trovato!");
        // se non esiste, mando errore

        return "errorPage"; // apro pagina errore
    }

    // SELL (punto 6)
    @GetMapping("/items/sell") // http://localhost:8080/items/sell?code=<code>
    public String sellItem(@RequestParam String code, Model model) {
        // @RequestParam prende il parametro ?code=...

        boolean success = itemService.sellItem(code);
        // chiamo il service per vendere l'item

        if (!success) { // se fallisce
            model.addAttribute("error", "Errore vendita");
            // metto messaggio errore

            return "errorPage"; // pagina errore
        }

        return "redirect:/items/" + code;
        // redirect alla pagina dettaglio
    }

    // ADD (punto 7)
    @GetMapping("/items/add") //http://localhost:8080/items/add?code=<code>&number=<number>
    public String addItem(@RequestParam String code, @RequestParam int number, Model model) {
        // prende code e number dall'URL

        boolean success = itemService.addItemCount(code, number);
        // chiamo il service per aumentare quantità

        if (!success) { // se errore
            model.addAttribute("error", "Errore aggiunta");
            // messaggio errore

            return "errorPage"; // pagina errore
        }

        return "redirect:/items/" + code; //http://localhost:8080/items/<code>
        // redirect alla pagina dettaglio
    }

    // FORM INSERT (punto 8)
    @GetMapping("/items/insert") // apre pagina form
    public String loadInsertPage(@ModelAttribute Item item) {
        // crea oggetto vuoto per il form

        return "insertItem"; // apre pagina insertItem.html
    }

    // SALVA ITEM (punto 8)
    @PostMapping("/items/insert") //http://localhost:8080/items/insert
    public String saveItem(@Valid Item item, Errors errors) { //qua viene chiesto di validare
        // @Valid attiva controlli, Errors contiene errori

        if (errors.hasErrors()) { // se ci sono errori
            return "insertItem"; // resto nella pagina
        }

        itemService.save(item);
        // salvo item nel database

        return "redirect:/items";
        // torno alla lista
    }
}