package ch.samt.moviesandactors.controller;

import ch.samt.moviesandactors.domain.Director;
import ch.samt.moviesandactors.service.DirectorService;
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
@RequestMapping("/directors")
public class DirectorController {

    private final DirectorService directorService;

    @Autowired
    public DirectorController(DirectorService directorService) {
        this.directorService = directorService;
    }

    @GetMapping("/add-director")
    public String loadInsertdirectorPage(@ModelAttribute Director director, Model model) {
        model.addAttribute("director", director);
        return "insertDirector";
    }

    @PostMapping("/add-director")
    public String insertMovie(@Valid Director director, Errors errors) {
        if (errors.hasErrors()) {
            return "insertDirector";
        }
        directorService.save(director);
        return "redirect:/movies";
    }



}
