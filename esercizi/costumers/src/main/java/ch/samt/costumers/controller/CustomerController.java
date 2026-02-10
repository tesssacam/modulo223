package ch.samt.costumers.controller;

import ch.samt.costumers.domain.Customer;
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
                    new Customer(1L, "mario", "rossi", 40),
                    new Customer(2L, "giorgio", "verdi", 30),
                    new Customer(3L, "ennio", "bianchi", 33)

            ));


    @GetMapping("/customers")
    public String loadcustomers(Model model, @RequestParam(value = "id",
            required = false) Long customerId) {
        if (customerId == null) {
            model.addAttribute("customers", customers);
        } else {
            Customer customer = customers.stream()
                    .filter(c -> c.getId().equals(customerId))
                    .findFirst().orElse(null);
            model.addAttribute("customers", customer);
        }
        return "customersList";
    }


    @GetMapping("/customers/{id}")
    public String getCustomerByPath(Model model, @PathVariable("id") Long customerId) {

        Customer customer = customers.stream()
                .filter(c -> c.getId().equals(customerId))
                .findFirst().orElse(null);
        model.addAttribute("customers", customer);
        return "customersList";
    }


    @GetMapping("/insert")
    public String loadInsertPage(Model model) {
        model.addAttribute("customer", new Customer());
        return "insertCustomer";
    }

    @PostMapping("/insert")
    public String saveCustomer(@ModelAttribute Customer customer, Model model) {
        customers.add(customer);
        model.addAttribute("customers", customers);
        return "customersList";
    }

    @PostMapping
    public String saveCustomer(@Valid Customer costumer, Errors errors) {
        if (errors.hasErrors()) {
            return "insertCustomer";
        }

        customers.add(costumer);
        return "redirect:/customers/";
    }

}