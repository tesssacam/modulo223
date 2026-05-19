package ch.samt.moviesandactors.service;

import ch.samt.moviesandactors.domain.Movie;
import ch.samt.moviesandactors.repository.ActorRepository;
import ch.samt.moviesandactors.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository, ActorRepository actorRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public Movie findById(Long movieId) {
        return movieRepository.findById(movieId).orElseThrow();
    }

    public void save(Movie movie) {
        movieRepository.save(movie);
    }
}
