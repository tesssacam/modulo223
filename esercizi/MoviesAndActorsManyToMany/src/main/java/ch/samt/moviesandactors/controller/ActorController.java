package ch.samt.moviesandactors.controller;

import ch.samt.moviesandactors.domain.Actor;
import ch.samt.moviesandactors.service.ActorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/actors")
public class ActorController {

    private final ActorService actorService;

    @Autowired
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping("/add-actor")
    public String loadInsertActorPage(@ModelAttribute Actor actor, Model model) {
        model.addAttribute("actor", actor);
        return "insertActor";
    }

    @PostMapping("/add-actor")
    public String insertMovie(@Valid Actor actor, Errors errors) {
        if (errors.hasErrors()) {
            return "insertActor";
        }
        actorService.save(actor);
        return "redirect:/movies";
    }


}
