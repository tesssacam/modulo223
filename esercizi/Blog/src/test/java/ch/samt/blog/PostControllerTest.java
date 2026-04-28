package ch.samt.blog;

import ch.samt.blog.domain.Post;
import ch.samt.blog.service.PostService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PostService postService;

    private Post post;

    @BeforeEach
    void setup() {

        post = new Post();
        post.setId(1L);
        post.setTitle("Test");
        post.setAuthor("mario");
        post.setCategory("tech");
        post.setPublicationdate("2026");
        post.setLikenumber(5);
        post.setContent("content");
    }

    @Test
    void testHome() throws Exception {

        when(postService.getAllPosts()).thenReturn(Arrays.asList(post));

        mockMvc.perform(get("/blog"))
                .andExpect(status().isOk())
                .andExpect(view().name("blogList"));
    }

    @Test
    void testBest() throws Exception {

        when(postService.getBestPosts()).thenReturn(Arrays.asList(post));

        mockMvc.perform(get("/blog/best"))
                .andExpect(status().isOk())
                .andExpect(view().name("blogList"));
    }
}