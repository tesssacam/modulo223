package ch.samt.gardenwarehouse;

import ch.samt.gardenwarehouse.domain.Item;
import ch.samt.gardenwarehouse.service.ItemService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest // avvia Spring
@AutoConfigureMockMvc // abilita MockMvc
@Transactional // rollback dopo test
public class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc; // simula HTTP

    @Autowired
    private ItemService itemService;

    private Item item1;

    @BeforeEach
    void setUp() {

        // creo oggetto test
        item1 = new Item();
        item1.setId(1L);
        item1.setCode("abc-01");
        item1.setType("pianta");
        item1.setName("Lavanda");
        item1.setPrice(12.5);
        item1.setItemCount(5);
    }

    // TEST LISTA
    @Test
    void testLoadItems() throws Exception {

        when(itemService.getAllItems()).thenReturn(Arrays.asList(item1));

        mockMvc.perform(get("/items"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("items"))
                .andExpect(view().name("itemList"));
    }

    // TEST DETTAGLIO OK
    @Test
    void testLoadItemDetailFound() throws Exception {

        when(itemService.findByCode("abc-01")).thenReturn(Optional.of(item1));

        mockMvc.perform(get("/items/abc-01"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("item"))
                .andExpect(view().name("itemDetail"));
    }

    // TEST DETTAGLIO ERRORE
    @Test
    void testLoadItemDetailNotFound() throws Exception {

        when(itemService.findByCode("xxx-99")).thenReturn(Optional.empty());

        mockMvc.perform(get("/items/xxx-99"))
                .andExpect(model().attributeExists("error"))
                .andExpect(view().name("errorPage"));
    }

    // TEST SELL OK
    @Test
    void testSellItemSuccess() throws Exception {

        when(itemService.sellItem("abc-01")).thenReturn(true);

        mockMvc.perform(get("/items/sell").param("code", "abc-01"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/items/abc-01"));
    }

    // TEST SELL ERRORE
    @Test
    void testSellItemFail() throws Exception {

        when(itemService.sellItem("xxx-99")).thenReturn(false);

        mockMvc.perform(get("/items/sell").param("code", "xxx-99"))
                .andExpect(model().attributeExists("error"))
                .andExpect(view().name("errorPage"));
    }

    // TEST ADD OK
    @Test
    void testAddItemSuccess() throws Exception {

        when(itemService.addItemCount("abc-01", 3)).thenReturn(true);

        mockMvc.perform(get("/items/add")
                        .param("code", "abc-01")
                        .param("number", "3"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/items/abc-01"));
    }

    // TEST ADD ERRORE
    @Test
    void testAddItemFail() throws Exception {

        when(itemService.addItemCount("xxx-99", 3)).thenReturn(false);

        mockMvc.perform(get("/items/add")
                        .param("code", "xxx-99")
                        .param("number", "3"))
                .andExpect(model().attributeExists("error"))
                .andExpect(view().name("errorPage"));
    }
}