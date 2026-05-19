package ch.samt.moviesandactors.controller;

import ch.samt.moviesandactors.domain.Actor;
import ch.samt.moviesandactors.domain.Director;
import ch.samt.moviesandactors.domain.Movie;
import ch.samt.moviesandactors.service.ActorService;
import ch.samt.moviesandactors.service.DirectorService;
import ch.samt.moviesandactors.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;
    private final ActorService actorService;
    private final DirectorService directorService;

    @Autowired
    public MovieController(MovieService movieService, ActorService actorService, DirectorService directorService) {
        this.movieService = movieService;
        this.actorService = actorService;
        this.directorService = directorService;
    }

    @GetMapping()
    public String showMovies(Model model) {
        List<Movie> movies = movieService.findAll();
        model.addAttribute("movies", movies);
        return "movies";
    }

    @GetMapping("/{movieId}")
    public String showSingleMovie(@PathVariable Long movieId, Model model) {
        Movie movie = movieService.findById(movieId);
        model.addAttribute("movie", movie);
        return "singleMovie";
    }

    @GetMapping("/add-movie")
    public String loadInsertMoviePage(@ModelAttribute Movie movie, Model model) {
        List<Director> directors = directorService.findAll();
        List<Actor> actors = actorService.findAll();
        model.addAttribute("directors", directors);
        model.addAttribute("actors", actors);
        return "insertMovie";
    }

    @PostMapping("/add-movie")
    public String insertMovie(@Valid Movie movie, Errors errors, Model model) {
        if (errors.hasErrors()) {
            List<Director> directors = directorService.findAll();
            List<Actor> actors = actorService.findAll();
            model.addAttribute("directors", directors);
            model.addAttribute("actors", actors);
            return "insertMovie";
        }
        movieService.save(movie);
        return "redirect:/movies";
    }

    @GetMapping("/add-actors-to-movie")
    public String loadPageActorToMovie(Model model) {
        List<Movie> movies = movieService.findAll();
        List<Actor> actors = actorService.findAll();
        model.addAttribute("movies", movies);
        model.addAttribute("actors", actors);
        return "addActorToMovie";
    }

    @PostMapping("/add-actors-to-movie")
    public String addActorToMovie(
            @RequestParam("movieId") Long movieId,
            @RequestParam("selectedActors") List<Long> selectedActorIds) {
        Movie movie = movieService.findById(movieId);
        List<Actor> selectedActors = actorService.findAllByIds(selectedActorIds);
        movie.getActors().addAll(selectedActors);
        movieService.save(movie);
        return "redirect:/movies/" + movieId;
    }

}