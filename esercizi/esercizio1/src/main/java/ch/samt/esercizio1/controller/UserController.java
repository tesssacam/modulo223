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
    // si usa hash per quando si vuole cercare un'utente direttamente dall'id users.get(1L)
    private final Map<Long, User> users = new HashMap<>(); //prima era una lista ora è una mappa


    // Pagina inserimento utenti
    // Tutti gli URL di questa classe iniziano con /user
    @GetMapping("/insert")
    public String insertUser(Model model) {

        model.addAttribute("user", new User()); //collega il form all'oggetto User
        model.addAttribute("users", users.values()); //passa tutti gli utenti alla tabella

        return "user-insert"; //dice di aprire la pagina html user-insert
    }


    // Salvataggio utente

    @PostMapping("/insert")
    public String saveUser(@ModelAttribute User user) { //quando clicci salva -> prende i dati dal form crea un'oggetto User
                            // @ModelAttribute -> leggere i dati dal form
        users.put(user.getId(), user); //salva gli utenti nella hashmap usando l'id come chiave
        return "redirect:/user/insert"; //evitare il problema del refresh del form
                                        //se no ogni refresh reinserirebbe lo stesso utente.
    }


    // Punto 9 e 10: carica utente con query string
    ///user/load?userId=1
    @GetMapping("/load")
    public String loadUser(@RequestParam Long userId, Model model) {  //model->passare i dati alla pagina

        User user = users.get(userId);  // Cerchiamo l'utente nella HashMap

        if (user == null) {
            // Se non esiste
            model.addAttribute("error", "User not found");
        } else {
            // Se esiste
            model.addAttribute("user", user);
        }

        return "user-load";
    }
}
