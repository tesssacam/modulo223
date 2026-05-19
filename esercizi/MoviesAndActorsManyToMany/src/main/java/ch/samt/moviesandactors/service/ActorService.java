package ch.samt.moviesandactors.service;

import ch.samt.moviesandactors.domain.Actor;
import ch.samt.moviesandactors.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorService {

    private final ActorRepository actorRepository;

    @Autowired
    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public List<Actor> findAll() {
        return actorRepository.findAll();
    }

    public Actor findById(Long actorId) {
        return actorRepository.findById(actorId).orElseThrow();
    }

    public void save(Actor actor) {
        actorRepository.save(actor);
    }

    public List<Actor> findAllByIds(List<Long> selectedActorIds) {
        return actorRepository.findAllById(selectedActorIds);
    }
}
