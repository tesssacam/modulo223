package ch.samt.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc; // simulatore di chiamate http

    @Test
    void testGetProducts() throws Exception {
        mockMvc.perform(get("/products")) // esegue GET su /products
                .andExpect(status().isOk()); // verifica che la risposta sia  OK
    }

    @Test
    void testShowProduct() throws Exception {
        mockMvc.perform(get("/products/show/1")) // GET prodotto esistente
                .andExpect(status().isOk()); // deve restituire
    }

    @Test
    void testProductNotFound() throws Exception {
        mockMvc.perform(get("/products/show/999")) // GET prodotto non esistente
                .andExpect(status().isOk()); // ritorna comunque  (pagina error)
    }
}

//risposta domanda:
//no
//perché la chiave della HashMap è unica
//se si inserisce lo stesso id → il nuovo prodotto sovrascrive quello vecchio