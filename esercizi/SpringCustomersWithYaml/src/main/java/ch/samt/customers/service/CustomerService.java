package ch.samt.customers;

// repository per controlli DB
import ch.samt.customers.data.CustomerRepository;

// entity
import ch.samt.customers.domain.Customer;

// test Spring
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

// Mock MVC per simulare richieste HTTP
import org.springframework.test.web.servlet.MockMvc;

// transazioni rollback automatico
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// test controller Spring Boot
@SpringBootTest
@Transactional
public class CustomerControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerRepository customerRepository;

    // TEST: caricamento lista clienti
    @Test
    public void testLoadCustomers() throws Exception {

        List<Customer> allCustomers = customerRepository.findAll();

        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk())
                .andExpect(view().name("customerList"))
                .andExpect(model().attributeExists("customers"))
                .andExpect(model().attribute("customers", hasSize(allCustomers.size())));
    }

    // TEST: pagina inserimento
    @Test
    public void testLoadInsertPage() throws Exception {

        mockMvc.perform(get("/customers/insert"))
                .andExpect(status().isOk())
                .andExpect(view().name("insertCustomer"))
                .andExpect(model().attributeExists("customer"));
    }

    // TEST: salvataggio OK
    @Test
    public void testSaveCustomer_Success() throws Exception {

        mockMvc.perform(post("/customers/insert")
                        .param("name", "Gino")
                        .param("surname", "Bartali")
                        .param("age", "70")
                        .param("city", "lugano")
                        .param("ccnumber", "4242424242424242")
                        .param("ccexpiration", "03/28")
                        .param("cccvv", "538"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/customers"));

        List<Customer> result =
                customerRepository.findBySurnameIgnoreCase("Bartali");

        assertFalse(result.isEmpty());
    }

    // TEST: salvataggio con errori
    @Test
    public void testSaveCustomer_WithErrors() throws Exception {

        mockMvc.perform(post("/customers/insert")
                        .param("name", "Fausto")
                        .param("surname", "Coppi"))
                .andExpect(status().isOk())
                .andExpect(view().name("insertCustomer"))
                .andExpect(model().attributeHasFieldErrors("customer", "age"));
    }

    // TEST: filtro cognome
    @Test
    public void testLoadCustomersBySurname() throws Exception {

        mockMvc.perform(get("/customers/Rossi"))
                .andExpect(status().isOk())
                .andExpect(view().name("customerList"))
                .andExpect(model().attributeExists("customers"));
    }
}