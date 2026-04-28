package ch.samt.customers.controller;

// entity Customer
import ch.samt.customers.domain.Customer;

// service business logic
import ch.samt.customers.service.CustomerService;

// validazione input
import jakarta.validation.Valid;

// injection Spring
import org.springframework.beans.factory.annotation.Autowired;

// MVC controller
import org.springframework.stereotype.Controller;

// passaggio dati alle view
import org.springframework.ui.Model;

// gestione errori validazione
import org.springframework.validation.Errors;

// mapping HTTP
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

// controller MVC per gestione clienti
@Controller
public class CustomerController {

    // service con logica business
    private CustomerService customerService;

    // injection automatica del service
    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // ======================
    // LISTA CLIENTI
    // ======================
    @GetMapping("/customers")
    public String loadCustomers(Model model) {

        // recupera tutti i clienti dal DB
        model.addAttribute("customers", customerService.getAllCustomers());

        return "customerList";
    }

    // ======================
    // PAGINA INSERIMENTO CLIENTE
    // ======================
    @GetMapping("/customers/insert")
    public String loadInsertPage(@ModelAttribute Customer customer) {

        // apre form vuoto
        return "insertCustomer";
    }

    // ======================
    // SALVATAGGIO CLIENTE
    // ======================
    @PostMapping("/customers/insert")
    public String saveCustomers(@Valid Customer customer, Errors errors) {

        // se validazione fallisce
        if (errors.hasErrors()) {
            return "insertCustomer";
        }

        // salva cliente nel DB
        customerService.save(customer);

        return "redirect:/customers";
    }

    // ======================
    // FILTRO PER COGNOME (case insensitive)
    // ======================
    @GetMapping("/customers/{surnameToFilter}")
    public String loadFilteredCustomers(Model model, @PathVariable String surnameToFilter) {

        // ricerca clienti per cognome
        model.addAttribute("customers",
                customerService.findBySurnameIgnoreCase(surnameToFilter));

        return "customerList";
    }

    // ======================
    // ELIMINA CLIENTE
    // ======================
    @GetMapping("/customers/delete/{id}")
    public String deleteCustomer(@PathVariable Long id) {

        // elimina dal DB
        customerService.deleteById(id);

        return "redirect:/customers";
    }

    // ======================
    // PAGINA MODIFICA CLIENTE
    // ======================
    @GetMapping("/customers/edit/{id}")
    public String loadEditPage(@PathVariable Long id, Model model) {

        // cerca cliente per ID
        Optional<Customer> customer = customerService.findById(id);

        if (customer.isPresent()) {
            model.addAttribute("customer", customer.get());
            return "editCustomer";
        }

        // se non trovato torna alla lista
        return "redirect:/customers";
    }

    // ======================
    // UPDATE CLIENTE
    // ======================
    @PostMapping("/customers/edit")
    public String updateCustomer(@Valid Customer customer, Errors errors) {

        // controllo validazione
        if (errors.hasErrors()) {
            return "editCustomer";
        }

        // salva aggiornamento
        customerService.save(customer);

        return "redirect:/customers";
    }
}