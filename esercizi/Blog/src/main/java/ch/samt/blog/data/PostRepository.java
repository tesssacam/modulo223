package ch.samt.blog.data;

// entity Post
import ch.samt.blog.domain.Post;

// JPA repository già pronto (CRUD automatico)
import org.springframework.data.jpa.repository.JpaRepository;

// repository Spring
import org.springframework.stereotype.Repository;

import java.util.List;

// indica accesso ai dati DB
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    // trova tutti i post di un autore specifico
    List<Post> findByAuthor(String author);

    // trova i 2 post con più like (ordinati decrescente)
    List<Post> findTop2ByOrderByLikenumberDesc();
}