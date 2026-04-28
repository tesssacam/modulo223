package ch.samt.blog.controller;

import ch.samt.blog.domain.Post;
import ch.samt.blog.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    // ======================
    // Allo start-up dell’applicazione devono essere già presenti 2
    // post di esempio visibili alla pagina http://localhost:8080/blog
    // ======================
    @GetMapping("/blog")
    public String loadPosts(Model model) {

        model.addAttribute("posts", postService.getAllPosts());

        return "blogList";
    }

    // ======================
    //http://localhost:8080/blog/new per inserire un nuovo post e alla fine tornare alla home “/blog”
    // ======================
    @GetMapping("/blog/new")
    public String newPostPage(@ModelAttribute Post post) {
        return "newPost";
    }

    // ======================
    // SALVA POST (POST)
    // ======================
    @PostMapping("/blog/new")
    public String savePost(@Valid Post post, Errors errors) {

        if (errors.hasErrors()) {
            return "newPost";
        }

        postService.save(post);

        return "redirect:/blog";
    }

    // ======================
    // http://localhost:8080/blog/post?postid<postid> per visualizzare un singolo post
    // ======================
    @GetMapping("/blog/post")
    public String loadPost(@RequestParam Long postid, Model model) {

        Optional<Post> post = postService.getPostById(postid);

        if (post.isPresent()) {
            model.addAttribute("post", post.get());
            return "postDetail";
        }

        model.addAttribute("error", "Post not found");
        return "errorPage";
    }

    // ======================
    // http://localhost:8080/blog/<autore> per visualizzare tutti i post dell’autore passato nell’url
    // ======================
    @GetMapping("/blog/{author}")
    public String getByAuthor(@PathVariable String author, Model model) {

        model.addAttribute("posts", postService.getByAuthor(author));

        return "blogList";
    }

    // ======================
    // http://localhost:8080/blog/best per visualizzare i 2 post più votati
    // ======================
    @GetMapping("/blog/best")
    public String bestPosts(Model model) {

        model.addAttribute("posts", postService.getBestPosts());

        return "blogList";
    }

    // ======================
    // Nella pagina di elenco post, aggiungere una nuova colonna per attribuire un like al singolo post.
    // Fare in modo che al click sul like parta una chiamata a http://localhost:8080/blog/like?postid=<postid>
    // che vada ad incrementare il numero di like del post
    // ======================
    @GetMapping("/blog/like")
    public String likePost(@RequestParam Long postid) {

        postService.addLike(postid);

        return "redirect:/blog";
    }
}