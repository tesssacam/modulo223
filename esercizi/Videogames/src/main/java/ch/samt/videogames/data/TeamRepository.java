package ch.samt.videogames.data;

import org.springframework.data.repository.CrudRepository;
import ch.samt.videogames.domain.Team;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends CrudRepository<Team, Long> {
}
