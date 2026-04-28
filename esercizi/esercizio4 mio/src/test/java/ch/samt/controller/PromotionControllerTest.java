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
class PromotionControllerTest {

    @Autowired
    private MockMvc mockMvc; // simula le richieste HTTP

    @Test
    void testGetPromotions() throws Exception {
        mockMvc.perform(get("/promotions"))
                .andExpect(status().isOk()); // deve rispondere OK
    }

    @Test
    void testGetActivePromotions() throws Exception {
        mockMvc.perform(get("/promotions")
                        .param("activeOnly", "true"))
                .andExpect(status().isOk()); // deve filtrare solo quelle attive
    }

    @Test
    void testShowPromotion() throws Exception {
        mockMvc.perform(get("/promotions/show/1"))
                .andExpect(status().isOk()); // esistente
    }

    @Test
    void testPromotionNotFound() throws Exception {
        mockMvc.perform(get("/promotions/show/999"))
                .andExpect(status().isOk()); // errore ritorna pagina error
    }

    @Test
    void testAddPromotionSuccess() throws Exception {
        mockMvc.perform(post("/promotions/new")
                        .param("id", "10")
                        .param("productName", "Maglietta")
                        .param("originalPrice", "25")
                        .param("discountPercent", "10")
                        .param("startDate", "2026-03-05")
                        .param("endDate", "2026-03-15"))
                .andExpect(status().is3xxRedirection()); // redirect dopo inserimento corretto
    }

    @Test
    void testAddPromotionValidationError() throws Exception {
        mockMvc.perform(post("/promotions/new")
                        .param("id", "11")
                        .param("productName", "")
                        .param("originalPrice", "-5")
                        .param("discountPercent", "150")
                        .param("startDate", "2020-01-01")
                        .param("endDate", "2019-12-01"))
                .andExpect(status().isOk()); // torna al form per errori
    }
}