package ch.samt.videogames.data;

import ch.samt.videogames.domain.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PlayerRepository extends JpaRepository<Player, Long> {

}
