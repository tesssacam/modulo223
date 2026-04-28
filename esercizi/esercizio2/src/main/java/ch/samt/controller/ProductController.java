package ch.samt.controller;

import ch.samt.model.Product;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

// Dice a Spring che questa classe gestisce richieste web
@Controller
public class ProductController {

    // Crea una HashMap che simula un database in memoria
    // Long = chiave (id del prodotto)
    // Product = valore (oggetto prodotto)
    private static final Map<Long, Product> products = new HashMap<>();

    // Blocco statico: viene eseguito una sola volta all'avvio dell'applicazione
    static {

        // Inserisce un prodotto con id 1
        products.put(1L, new Product(1L, "latte", 2.5, LocalDate.now().plusDays(5), "latte fresco"));
        products.put(2L, new Product(2L, "pane", 1.8, LocalDate.now().plusDays(2), "pane integrale"));
        products.put(3L, new Product(3L, "yogurt", 0.9, LocalDate.now().plusDays(10), "yogurt alla stracciatella"));
    }

    // Questo metodo gestisce richieste GET su /products
    @GetMapping("/products")
    public String getProducts(

            // Parametro opzionale ?name=...
            @RequestParam(required = false) String name,

            // Parametro opzionale ?pricelessthan=...
            @RequestParam(required = false) Double pricelessthan,

            // Model serve per passare dati alla view
            Model model) {

        // Prende tutti i prodotti dalla HashMap
        var result = products.values();

        // Se il parametro name esiste e non è vuoto
        if (name != null && !name.isBlank()) {

            // Trasforma in stream
            result = result.stream()

                    // Filtra solo i prodotti con nome uguale (ignora maiuscole/minuscole)
                    .filter(p -> p.getName().equalsIgnoreCase(name))

                    // Converte di nuovo in lista
                    .collect(Collectors.toList());
        }

        // Se è stato passato un prezzo massimo
        if (pricelessthan != null) {

            // Filtra i prodotti con prezzo minore del valore indicato
            result = result.stream()
                    .filter(p -> p.getPrice() < pricelessthan)
                    .collect(Collectors.toList());
        }

        // Inserisce la lista dei prodotti nel Model
        model.addAttribute("products", result);

        // Restituisce la pagina products.html
        return "products";
    }

    // Questo metodo mostra un singolo prodotto
    // L'id viene preso dall'URL
    @GetMapping("/products/show/{id}")
    public String showProduct(

            // Prende il valore {id} dall'URL
            @PathVariable Long id,

            // Serve per passare dati alla view
            Model model) {

        // Cerca il prodotto nella HashMap usando l'id
        Product product = products.get(id);

        // Se non esiste nessun prodotto con quell'id
        if (product == null) {

            // Aggiunge un messaggio di errore
            model.addAttribute("error", "prodotto non trovato");

            // Mostra la pagina error.html
            return "error";
        }

        // Se esiste, lo passa alla pagina HTML
        model.addAttribute("product", product);

        // Mostra la pagina product.html
        return "product";
    }

    // Mostra il form per creare un nuovo prodotto
    @GetMapping("/newproduct")
    public String newProductForm(Model model) {

        // Crea un oggetto Product vuoto e lo collega al form
        model.addAttribute("product", new Product());

        // Mostra la pagina newproduct.html
        return "newproduct";
    }

    // Gestisce l'invio del form (POST)
    @PostMapping("/newproduct")
    public String addProduct(

            // Prende i dati dal form e attiva la validazione
            @Valid @ModelAttribute Product product,

            // Contiene eventuali errori di validazione
            Errors errors,

            // Serve per passare dati alla view
            Model model) {

        // Se ci sono errori di validazione
        if (errors.hasErrors()) {

            // Torna alla pagina del form
            return "newproduct";
        }

        // Se esiste già un prodotto con lo stesso id
        if (products.containsKey(product.getId())) {

            // Mostra messaggio di errore
            model.addAttribute("error", "esiste già un prodotto con questo ID");

            // Mostra pagina error.html
            return "error";
        }

        // Inserisce il nuovo prodotto nella HashMap
        products.put(product.getId(), product);

        // Redirect alla lista prodotti per evitare doppio invio del form
        return "redirect:/products";
    }
}