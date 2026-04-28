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
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;
    // MockMvc simula chiamate HTTP senza browser

    @Test
    void testGetProducts() throws Exception {

        mockMvc.perform(get("/products"))
                // simulo una richiesta GET su /products

                .andExpect(status().isOk());
        // verifico che lo status sia 200 OK
    }

    @Test
    void testShowProduct() throws Exception {

        mockMvc.perform(get("/products/show/1"))
                // prodotto esistente

                .andExpect(status().isOk());
        // deve rispondere correttamente
    }

    @Test
    void testProductNotFound() throws Exception {

        mockMvc.perform(get("/products/show/999"))
                // prodotto che non esiste

                .andExpect(status().isOk());
        // ritorna comunque pagina error (status 200)
    }

    @Test
    void testAddProductSuccess() throws Exception {

        mockMvc.perform(post("/newproduct")
                        .param("id", "10")
                        .param("name", "acqua")
                        .param("price", "1.5")
                        .param("expirationDate", "2026-12-01")
                        .param("description", "acqua naturale"))
                .andExpect(status().is3xxRedirection());
        // deve fare redirect dopo inserimento corretto
    }

    @Test
    void testAddProductValidationError() throws Exception {

        mockMvc.perform(post("/newproduct")
                        .param("id", "20")
                        .param("name", "") // nome vuoto → errore
                        .param("price", "-5") // prezzo negativo → errore
                        .param("expirationDate", "2020-01-01") // data passata → errore
                        .param("description", "abc")) // troppo corta
                .andExpect(status().isOk());
        // ritorna al form perché ci sono errori
    }
}

//risposta domanda:
//no
//perché la chiave della HashMap è unica
//se si inserisce lo stesso id → il nuovo prodotto sovrascrive quello vecchio

/*🎯 COME SCRIVERE TEST IN ALTRI ESERCIZI

Se domani ti danno un altro controller, tu devi:

🔹 1️⃣ Test GET semplice
mockMvc.perform(get("/nomeurl"))
       .andExpect(status().isOk());


🔹 2️⃣ Test con parametro
mockMvc.perform(get("/products")
       .param("name", "latte"))
       .andExpect(status().isOk());


🔹 3️⃣ Test POST
mockMvc.perform(post("/newproduct")
        .param("id", "10")
        .param("name", "acqua")
        .param("price", "1.5")
        .param("expirationDate", "2026-12-01")
        .param("description", "acqua naturale"))
        .andExpect(status().is3xxRedirection());

Qui controlli che faccia redirect.

🔹 4️⃣ Test validazione fallita
mockMvc.perform(post("/newproduct")
        .param("id", "20")
        .param("name", "") // nome vuoto
        .param("price", "-5") // prezzo negativo
        .param("expirationDate", "2020-01-01") // data passata
        .param("description", "abc"))
        .andExpect(status().isOk());

Perché torna alla pagina form.
*/
