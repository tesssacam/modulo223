package ch.samt.videogames.service;

import ch.samt.videogames.data.PlayerRepository;
import ch.samt.videogames.domain.Player;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository){
        this.playerRepository = playerRepository;
    }

    public List<Player> findAll(){
        return playerRepository.findAll();
    }

    public void save(@Valid Player player) {
        try {
            playerRepository.save(player);
        } catch (RuntimeException ex) {
            throw new RuntimeException("Errore durante il save del player in DB: " + player, ex);
        }
    }
}
