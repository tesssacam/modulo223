package ch.samt.blog.service;

// repository accesso DB
import ch.samt.blog.data.PostRepository;

// entity Post
import ch.samt.blog.domain.Post;

// service Spring
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// logica applicativa del blog
@Service
public class PostService {

    // repository per DB
    private final PostRepository postRepository;

    // injection automatica
    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // prende tutti i post (home)
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    // cerca post per ID
    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    // salva post nel DB
    public void save(Post post) {
        postRepository.save(post);
    }

    // filtra per autore
    public List<Post> getByAuthor(String author) {
        return postRepository.findByAuthor(author);
    }

    // prende i 2 post con più like
    public List<Post> getBestPosts() {
        return postRepository.findTop2ByOrderByLikenumberDesc();
    }

    // aumenta like di un post
    public void addLike(Long id) {

        Optional<Post> postOpt = getPostById(id);

        // se esiste
        if (postOpt.isPresent()) {

            Post post = postOpt.get();

            // incrementa like
            post.setLikenumber(post.getLikenumber() + 1);

            // salva aggiornamento
            postRepository.save(post);
        }
    }
}