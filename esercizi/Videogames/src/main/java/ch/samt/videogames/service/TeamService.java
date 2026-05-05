package ch.samt.videogames.service;

import ch.samt.videogames.data.TeamRepository;
import ch.samt.videogames.domain.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {
    private final TeamRepository teamRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<Team> findAll() {
        return (List<Team>) teamRepository.findAll();
    }
}
