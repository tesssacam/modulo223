package ch.samt.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ArticleControllerTest {

    @Autowired
    private MockMvc mockMvc;
    // Simula chiamate HTTP senza browser

    @Test
    void testGetActiveArticles() throws Exception {
        mockMvc.perform(get("/articles"))
                .andExpect(status().isOk()); // deve mostrare articoli attivi
    }

    @Test
    void testGetDeletedArticles() throws Exception {
        mockMvc.perform(get("/articles/deleted"))
                .andExpect(status().isOk()); // deve mostrare articoli eliminati
    }

    @Test
    void testShowArticle() throws Exception {
        mockMvc.perform(get("/articles/show/1"))
                .andExpect(status().isOk()); // articolo esistente
    }

    @Test
    void testArticleNotFound() throws Exception {
        mockMvc.perform(get("/articles/show/999"))
                .andExpect(status().isOk()); // errore gestito
    }

    @Test
    void testAddArticleSuccess() throws Exception {
        mockMvc.perform(post("/articles/new")
                        .param("id", "10")
                        .param("title", "Nuovo Libro")
                        .param("author", "Mario")
                        .param("pages", "120"))
                .andExpect(status().is3xxRedirection()); // redirect dopo salvataggio
    }

    @Test
    void testAddArticleValidationError() throws Exception {
        mockMvc.perform(post("/articles/new")
                        .param("id", "11")
                        .param("title", "")
                        .param("author", "")
                        .param("pages", "-5"))
                .andExpect(status().isOk()); // torna al form per errori
    }

    @Test
    void testDeleteArticle() throws Exception {
        mockMvc.perform(get("/articles/delete/1"))
                .andExpect(status().is3xxRedirection()); // soft delete → redirect
    }
}