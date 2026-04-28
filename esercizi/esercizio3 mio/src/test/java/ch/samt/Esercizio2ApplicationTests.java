package ch.samt;

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
class CustomerControllerTest {

	@Autowired
	private MockMvc mockMvc;
	// MockMvc permette di simulare richieste HTTP al controller senza usare il browser

	// 🔹 Test GET: lista clienti
	@Test
	void testGetCustomers() throws Exception {
		mockMvc.perform(get("/customers"))
				// simulo richiesta GET su /customers
				.andExpect(status().isOk());
		// verifico che la risposta sia 200 OK
	}

	// 🔹 Test GET: mostra cliente esistente
	@Test
	void testShowCustomer() throws Exception {
		mockMvc.perform(get("/customers/show/1"))
				// cliente esistente con id=1
				.andExpect(status().isOk());
		// deve rispondere correttamente
	}

	// 🔹 Test GET: cliente non trovato
	@Test
	void testCustomerNotFound() throws Exception {
		mockMvc.perform(get("/customers/show/999"))
				// cliente con id inesistente
				.andExpect(status().isOk());
		// ritorna comunque pagina error (status 200)
	}

	// 🔹 Test GET: prenotazioni filtrate per data
	@Test
	void testGetCustomersWithDateFilter() throws Exception {
		mockMvc.perform(get("/customers")
						.param("bookingDate", "2026-03-10"))
				// filtro i clienti che hanno prenotazioni il 10 marzo 2026
				.andExpect(status().isOk());
		// deve ritornare la lista filtrata
	}

	// 🔹 Test POST: aggiungi nuovo cliente con prenotazione
	@Test
	void testAddCustomerSuccess() throws Exception {
		mockMvc.perform(post("/customers/new")
						.param("id", "10")
						.param("name", "Mario Rossi")
						.param("email", "mario.rossi@mail.com")
						.param("bookingDate", "2026-03-20"))
				.andExpect(status().is3xxRedirection());
		// redirect corretto dopo inserimento valido
	}

	// 🔹 Test POST: errore di validazione
	@Test
	void testAddCustomerValidationError() throws Exception {
		mockMvc.perform(post("/customers/new")
						.param("id", "11")
						.param("name", "") // nome vuoto → errore
						.param("email", "mario.rossi") // email non valida → errore
						.param("bookingDate", "2020-01-01")) // data passata → errore
				.andExpect(status().isOk());
		// torna al form perché ci sono errori
	}

	// 🔹 Test POST: ID duplicato
	@Test
	void testAddCustomerDuplicateId() throws Exception {
		mockMvc.perform(post("/customers/new")
						.param("id", "1") // ID già esistente
						.param("name", "Luca Bianchi")
						.param("email", "luca@mail.com")
						.param("bookingDate", "2026-03-25"))
				.andExpect(status().isOk());
		// ritorna pagina error con messaggio ID già esistente
	}
}