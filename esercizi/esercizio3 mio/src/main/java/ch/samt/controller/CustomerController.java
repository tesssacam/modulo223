package ch.samt.controller;

import ch.samt.model.Customer;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class CustomerController {

    private static final Map<Long, Customer> customers = new HashMap<>();

    static {
        // blocco statico popola la mappa clienti all'avvio
        customers.put(1L, new Customer(1L,"Luca","luca@email.com",25,"Cliente fedele"));
        customers.put(2L, new Customer(2L,"Maria","maria@email.com",30,"Cliente nuovo"));
        customers.put(3L, new Customer(3L,"Giulia","giulia@email.com",22,"Ha richiesto info"));
    }

    @GetMapping("/customers")
    public String getCustomers(@RequestParam(required=false) String name,
                               @RequestParam(required=false) Integer minAge,
                               Model model) {

        var result = customers.values();

        if (name != null && !name.isBlank()) {
            result = result.stream()
                    .filter(c -> c.getName().equalsIgnoreCase(name))
                    .collect(Collectors.toList());
        }

        if (minAge != null) {
            result = result.stream()
                    .filter(c -> c.getAge() >= minAge)
                    .collect(Collectors.toList());
        }

        model.addAttribute("customers", result);
        return "customers";
    }

    @GetMapping("/customers/show/{id}")
    public String showCustomer(@PathVariable Long id, Model model) {
        Customer customer = customers.get(id);
        if (customer == null) {
            model.addAttribute("error","Cliente non trovato");
            return "error";
        }
        model.addAttribute("customer", customer);
        return "customer";
    }

    @GetMapping("/customers/new")
    public String newCustomerForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "newcustomer";
    }

    @PostMapping("/customers/new")
    public String addCustomer(@Valid @ModelAttribute Customer customer,
                              Errors errors,
                              Model model) {

        if (errors.hasErrors()) {
            return "newcustomer";
        }

        if (customers.containsKey(customer.getId())) {
            model.addAttribute("error","Esiste già un cliente con questo ID");
            return "error";
        }

        customers.put(customer.getId(), customer);
        return "redirect:/customers";
    }
}