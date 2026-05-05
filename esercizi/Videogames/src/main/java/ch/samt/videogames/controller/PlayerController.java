package ch.samt.videogames.controller;

import ch.samt.videogames.domain.Player;
import ch.samt.videogames.service.PlayerService;
import ch.samt.videogames.service.TeamService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("players")
@Controller
public class PlayerController{
    private final PlayerService playerService;

    private final TeamService teamService;

    @Autowired
    public PlayerController(PlayerService playerService, TeamService teamService) {
        this.playerService = playerService;
        this.teamService = teamService;
    }

    @GetMapping
    public String loadPlayers(Model model){
        model.addAttribute("players",playerService.findAll());
        return "playerList";
    }

    @GetMapping("/insert")
    public String loadInsertPage(@ModelAttribute Player player) {
        return "insertPlayer";
    }

    @PostMapping("/insert")
    public String savePlayers(@Valid Player player, Errors errors) {
        if (errors.hasErrors()) {
            return "insertPlayer";
        }
        playerService.save(player);
        return "redirect:/customers";
    }






}