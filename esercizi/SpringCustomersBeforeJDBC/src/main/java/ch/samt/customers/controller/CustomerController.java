package ch.samt.customers.controller;

import ch.samt.customers.domain.Customer;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequestMapping("/customers")
@Controller
public class CustomerController {

    private final List<Customer> customers = new ArrayList<>(
            Arrays.asList(
                    new Customer(1L, "Mario", "Rossi", 40),
                    new Customer(2L, "Giorgio", "Verdi", 30),
                    new Customer(3L, "Ennio", "Bianchi", 33)
            ));

    @GetMapping
    public String loadCustomers(Model model) {
        model.addAttribute("customers", customers);
        return "customerList";
    }

    @GetMapping("/insert")
    public String loadInsertPage(@ModelAttribute Customer customer) {
        return "insertCustomer";
    }

    @PostMapping("/insert")
    public String saveCustomers(@Valid Customer customer, Errors errors) {
        if (errors.hasErrors()) {
            return "insertCustomer";
        }
        customers.add(customer);
        return "redirect:/customers";
    }

    @GetMapping("/{surnameToFilter}")
    public String loadInsertPage(Model model, @PathVariable String surnameToFilter) {
        List<Customer> filteredCustomers = customers.stream().filter(customer -> customer.getSurname().equalsIgnoreCase(surnameToFilter)).toList();
        model.addAttribute("customers", filteredCustomers);
        return "customerList";
    }

}