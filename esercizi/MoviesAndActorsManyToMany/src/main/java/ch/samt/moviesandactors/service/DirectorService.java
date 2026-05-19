package ch.samt.moviesandactors.service;

import ch.samt.moviesandactors.domain.Director;
import ch.samt.moviesandactors.repository.DirectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectorService {

    private final DirectorRepository directorRepository;

    @Autowired
    public DirectorService(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    public List<Director> findAll() {
        return directorRepository.findAll();
    }

    public Director findById(Long directorId) {
        return directorRepository.findById(directorId).orElseThrow();
    }

    public void save(Director director) {
        directorRepository.save(director);
    }
}
