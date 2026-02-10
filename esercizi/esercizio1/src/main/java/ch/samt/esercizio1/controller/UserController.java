package ch.samt.esercizio1.controller;

import ch.samt.esercizio1.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    // Punto 8: HashMap per salvare utenti
    private final Map<Long, User> users = new HashMap<>();


    // Pagina inserimento utenti
    @GetMapping("/insert")
    public String insertUser(Model model) {

        model.addAttribute("user", new User());
        model.addAttribute("users", users.values());

        return "user-insert";
    }


    // Salvataggio utente
    @PostMapping("/insert")
    public String saveUser(@ModelAttribute User user) {

        users.put(user.getId(), user);
        return "redirect:/user/insert";
    }


    // Punto 9 e 10: carica utente con query string
    @GetMapping("/load")
    public String loadUser(@RequestParam Long userId, Model model) {

        User user = users.get(userId);

        if (user == null) {
            model.addAttribute("error", "User not found");
        } else {
            model.addAttribute("user", user);
        }

        return "user-load";
    }
}
