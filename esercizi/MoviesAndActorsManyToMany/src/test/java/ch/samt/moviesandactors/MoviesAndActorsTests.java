package ch.samt.moviesandactors;

import ch.samt.moviesandactors.domain.Actor;
import ch.samt.moviesandactors.domain.Movie;
import ch.samt.moviesandactors.repository.ActorRepository;
import ch.samt.moviesandactors.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc  // Abilita l'uso di MockMvc che ci permette di simulare le chiamate HTTP (GET, POST, ecc..)
@SpringBootTest  // Avvia l'applicazione Spring con tutte le sue componenti
@Transactional
public class MoviesAndActorsTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private ActorRepository actorRepository;

    @Test
    public void testLoadMovies() throws Exception {
        mockMvc.perform(get("/movies"))
                .andExpect(status().isOk())
                .andExpect(view().name("movies"))
                .andExpect(model().attribute("movies", movieRepository.findAll()))
                .andExpect(model().attribute("movies", hasItem(hasProperty("title", is("Ritorno al futuro")))));
    }

    @Test
    public void testInsertNewActor() throws Exception {
        // Verifica che De Niro NON sia presente in DB
        assertThat(actorRepository.findBySurname("De Niro")).isNull();

//       Anche se stiamo usando H2 con persistenza su file, i test sono idempotenti (cioè non modificano davvero lo stato del DB)
//       e funzionano anche se lanciati più volte. In questo caso ci si aspetterebbe che dopo aver inserito De Niro in DB al primo lancio del test,
//       rilanciandolo una seconda volta fallisca in quanto De Niro dovrebbe essere già presente. Questo non accade in quanto di default Spring
//       non salva davvero i dati in DB ma fa ROLLBACK ad ogni singolo test, proprio per garantire l'idempotenza (devo poter lanciare più volte il test
//       senza problemi) e anche per garantire che un test precedente non modifichi lo stato del DB e faccia potenzialmente fallire un test successivo.
        mockMvc.perform(post("/actors/add-actor")
                        .param("name", "Robert")
                        .param("surname", "De Niro"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/movies"));

        // Verifica che De Niro sia ora presente in DB
        assertThat(actorRepository.findBySurname("De Niro")).isNotNull();

    }

    @Test
    public void testInsertNewMovie() throws Exception {
        assertThat(movieRepository.findByTitle("Lo chiamavano Trinità")).isNull();
        long countBefore = movieRepository.count();
        mockMvc.perform(post("/movies/add-movie")
                        .param("title", "Lo chiamavano Trinità")
                        .param("description", "spaghetti western")
                        .param("director", "1") // va passato l'ID
                        .param("actors", "1", "3")) // vanno passati gli ID
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/movies"));

        assertThat(movieRepository.findByTitle("Lo chiamavano Trinità")).isNotNull();
        long countAfter = movieRepository.count();
        assertThat(countAfter).isEqualTo(countBefore + 1);

        Movie savedMovie = movieRepository.findByTitle("Lo chiamavano Trinità");
        assertThat(savedMovie).isNotNull();
        assertThat(savedMovie.getDirector().getId()).isEqualTo(1L);
        assertThat(savedMovie.getActors()).hasSize(2);
    }


}
