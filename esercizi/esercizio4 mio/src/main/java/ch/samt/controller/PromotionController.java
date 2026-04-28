package ch.samt.controller;

import ch.samt.model.Promotion;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class PromotionController {

    // HashMap "database" finto
    private static final Map<Long, Promotion> promotions = new HashMap<>();

    static {
        // blocco statico: popoliamo alcune promozioni iniziali
        promotions.put(1L, new Promotion(1L, "Felpa", 50.0, 20.0,
                LocalDate.now().plusDays(1), LocalDate.now().plusDays(10)));
        promotions.put(2L, new Promotion(2L, "Jeans", 80.0, 15.0,
                LocalDate.now().plusDays(2), LocalDate.now().plusDays(12)));
        promotions.put(3L, new Promotion(3L, "Scarpe", 120.0, 10.0,
                LocalDate.now(), LocalDate.now().plusDays(5)));
    }

    // GET: mostra tutte le promozioni, filtrabili per attive
    @GetMapping("/promotions")
    public String getPromotions(@RequestParam(required = false) Boolean activeOnly,
                                Model model) {
        var result = promotions.values();

        if (activeOnly != null && activeOnly) {
            LocalDate today = LocalDate.now();
            result = result.stream()
                    .filter(p -> !p.getStartDate().isAfter(today) && !p.getEndDate().isBefore(today))
                    .collect(Collectors.toList());
        }

        model.addAttribute("promotions", result);
        return "promotions";
    }

    // GET: dettagli singola promozione
    @GetMapping("/promotions/show/{id}")
    public String showPromotion(@PathVariable Long id, Model model) {
        Promotion promo = promotions.get(id);
        if (promo == null) {
            model.addAttribute("error","Promozione non trovata");
            return "error";
        }
        model.addAttribute("promotion", promo);
        return "promotion";
    }

    // GET: form nuovo promo
    @GetMapping("/promotions/new")
    public String newPromotionForm(Model model) {
        model.addAttribute("promotion", new Promotion());
        return "newpromotion";
    }

    // POST: aggiungi nuova promozione
    @PostMapping("/promotions/new")
    public String addPromotion(@Valid @ModelAttribute Promotion promotion,
                               Errors errors,
                               Model model) {

        // validazione form
        if (errors.hasErrors()) {
            return "newpromotion";
        }

        // controllo ID duplicato
        if (promotions.containsKey(promotion.getId())) {
            model.addAttribute("error", "ID già esistente");
            return "error";
        }

        // controllo data fine > inizio
        if (promotion.getEndDate().isBefore(promotion.getStartDate())) {
            model.addAttribute("error", "La data di fine deve essere dopo la data di inizio");
            return "error";
        }

        // salva promozione
        promotions.put(promotion.getId(), promotion);

        return "redirect:/promotions";
    }
}