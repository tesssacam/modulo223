package ch.samt.customers;

import ch.samt.customers.data.CustomerRepository;
import ch.samt.customers.domain.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
public class CustomerControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testLoadCustomers() throws Exception {
        List<Customer> allCustomers = customerRepository.findAll();

        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk())
                .andExpect(view().name("customerList"))
                .andExpect(model().attributeExists("customers"))
                .andExpect(model().attribute("customers", hasSize(allCustomers.size())))
                .andExpect(model().attribute("customers", hasItem(hasProperty("name", is("Mario")))));
    }

    @Test
    public void testLoadInsertPage() throws Exception {
        mockMvc.perform(get("/customers/insert"))
                .andExpect(status().isOk())
                .andExpect(view().name("insertCustomer"))
                .andExpect(model().attributeExists("customer"));
    }

    @Test
    public void testSaveCustomer_Success() throws Exception {
        mockMvc.perform(post("/customers/insert")
                        .param("name", "Gino")
                        .param("surname", "Bartali")
                        .param("age", "70")
                        .param("city","lugano")
                        .param("ccnumber","4242424242424242")
                        .param("ccexpiration","03/28")
                        .param("cccvv","538")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/customers"));

        List<Customer> result = customerRepository.findBySurnameIgnoreCase("Bartali");

        assertFalse(result.isEmpty());
        Customer savedCustomer = result.get(0);

        assertEquals("Gino", savedCustomer.getName());
        assertEquals("Bartali", savedCustomer.getSurname());
        assertEquals(70, savedCustomer.getAge());
    }

    @Test
    public void testSaveCustomer_WithErrors() throws Exception {
        mockMvc.perform(post("/customers/insert")
                        .param("name", "Fausto")
                        .param("surname", "Coppi"))
                .andExpect(status().isOk())
                .andExpect(view().name("insertCustomer"))
                .andExpect(model().attributeHasFieldErrors("customer", "age"));

        List<Customer> result = customerRepository.findBySurnameIgnoreCase("Coppi");
        assertTrue(result.isEmpty());
    }

    @Test
    public void testLoadCustomersBySurname() throws Exception {
        List<Customer> rossiCustomers = customerRepository.findBySurnameIgnoreCase("Rossi");

        mockMvc.perform(get("/customers/Rossi"))
                .andExpect(status().isOk())
                .andExpect(view().name("customerList"))
                .andExpect(model().attributeExists("customers"))
                .andExpect(model().attribute("customers", hasSize(rossiCustomers.size())));
    }
}