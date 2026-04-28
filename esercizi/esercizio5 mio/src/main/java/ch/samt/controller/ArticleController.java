package ch.samt.controller;

import ch.samt.model.Article;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class ArticleController {

    private static final Map<Long, Article> articles = new HashMap<>();
    // HashMap finta per “database”

    static {
        // Popolamento iniziale
        articles.put(1L, new Article(1L, "Java Base", "Mario Rossi", 120, false));
        articles.put(2L, new Article(2L, "Spring Boot Guida", "Luca Bianchi", 200, true)); // articolo eliminato
        articles.put(3L, new Article(3L, "Thymeleaf Avanzato", "Anna Verdi", 150, false));
    }

    // 🔹 GET: mostra articoli attivi
    @GetMapping("/articles")
    public String getArticles(Model model) {
        // filtro solo quelli non eliminati
        model.addAttribute("articles", articles.values()
                .stream()
                .filter(a -> !a.isDeleted())
                .collect(Collectors.toList()));
        return "articles"; // pagina articles.html
    }

    // 🔹 GET: mostra articoli eliminati
    @GetMapping("/articles/deleted")
    public String getDeletedArticles(Model model) {
        model.addAttribute("articles", articles.values()
                .stream()
                .filter(Article::isDeleted)
                .collect(Collectors.toList()));
        return "deleted-articles"; // pagina deleted-articles.html
    }

    // 🔹 GET: form nuovo articolo
    @GetMapping("/articles/new")
    public String newArticleForm(Model model) {
        model.addAttribute("article", new Article());
        return "new-article"; // pagina form
    }

    // 🔹 POST: aggiungi articolo
    @PostMapping("/articles/new")
    public String addArticle(@Valid @ModelAttribute Article article, Errors errors, Model model) {
        // validazione campi
        if(errors.hasErrors()) return "new-article";

        // ID duplicato
        if(articles.containsKey(article.getId())) {
            model.addAttribute("error", "ID già esistente");
            return "error";
        }

        // articolo nuovo non eliminato
        article.setDeleted(false);
        articles.put(article.getId(), article);

        return "redirect:/articles"; // redirect lista
    }

    // 🔹 GET: mostra dettaglio articolo
    @GetMapping("/articles/show/{id}")
    public String showArticle(@PathVariable Long id, Model model) {
        Article article = articles.get(id);
        if(article == null) {
            model.addAttribute("error", "Articolo non trovato");
            return "error";
        }
        model.addAttribute("article", article);
        return "article"; // pagina dettagli
    }

    // 🔹 GET: elimina articolo (soft delete)
    @GetMapping("/articles/delete/{id}")
    public String deleteArticle(@PathVariable Long id, Model model) {
        Article article = articles.get(id);
        if(article == null) {
            model.addAttribute("error", "Articolo non trovato");
            return "error";
        }
        article.setDeleted(true); // soft delete
        return "redirect:/articles";
    }
}