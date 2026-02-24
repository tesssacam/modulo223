package ch.samt.controller;

import ch.samt.model.Product;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class ProductController {

    private static final Map<Long, Product> products = new HashMap<>(); // database finto

    static {
        products.put(1L, new Product(1L, "latte", 2.5, LocalDate.now().plusDays(5), "latte fresco"));
        products.put(2L, new Product(2L, "pane", 1.8, LocalDate.now().plusDays(2), "pane integrale"));
        products.put(3L, new Product(3L, "yogurt", 0.9, LocalDate.now().plusDays(10), "yogurt alla stracciatella"));
    }

    @GetMapping("/products") // lista prodotti
    public String getProducts(
            @RequestParam(required = false) String name, // filtro nome
            @RequestParam(required = false) Double pricelessthan, // filtro prezzo
            Model model) {

        var result = products.values(); // prendo tutti i prodotti

        if (name != null && !name.isBlank()) { // se nome inserito
            result = result.stream()
                    .filter(p -> p.getName().equalsIgnoreCase(name)) // filtro per nome
                    .collect(Collectors.toList()); // converto in lista
        }

        if (pricelessthan != null) { // se prezzo inserito
            result = result.stream()
                    .filter(p -> p.getPrice() < pricelessthan) // filtro per prezzo
                    .collect(Collectors.toList()); // converto in lista
        }

        model.addAttribute("products", result); // passo alla view
        return "products"; // pagina products.html
    }

    @GetMapping("/products/show/{id}") // mostra singolo prodotto
    public String showProduct(@PathVariable Long id, Model model) {

        Product product = products.get(id); // cerco prodotto per id

        if (product == null) {
            model.addAttribute("error", "prodotto non trovato"); // messaggio errore
            return "error";
        }

        model.addAttribute("product", product); // passo prodotto
        return "product";
    }

    @GetMapping("/newproduct") // form nuovo prodotto
    public String newProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "newproduct"; // pagina form
    }

    @PostMapping("/newproduct")
    public String addProduct(@Valid @ModelAttribute Product product,
                             Errors errors,
                             Model model) {

        // controllo errori di validazione (es: campi vuoti)
        if (errors.hasErrors()) {
            return "newproduct";
        }

        // controllo se esiste già un prodotto con stesso ID
        if (products.containsKey(product.getId())) {
            model.addAttribute("error", "esiste già un prodotto con questo ID");
            return "error";
        }

        // salvo prodotto
        products.put(product.getId(), product);

        return "redirect:/products";
    }
}