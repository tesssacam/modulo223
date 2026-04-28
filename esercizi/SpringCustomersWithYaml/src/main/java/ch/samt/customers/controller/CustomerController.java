package ch.samt.customers.controller;

import ch.samt.customers.domain.Customer;
import ch.samt.customers.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping("/customers")
    public String loadCustomers(Model model) {
        model.addAttribute("customers", customerService.getAllCustomers());
        return "customerList";
    }



    @GetMapping("/customers/insert")
    public String loadInsertPage(@ModelAttribute Customer customer) {
        return "insertCustomer";
    }



    @PostMapping("/customers/insert")
    public String saveCustomers(@Valid Customer customer, Errors errors) {
        if (errors.hasErrors()) {
            return "insertCustomer";
        }

        customerService.save(customer);
        return "redirect:/customers";
    }


    @GetMapping("/customers/{surnameToFilter}")
    public String loadFilteredCustomers(Model model, @PathVariable String surnameToFilter) {
        model.addAttribute("customers", customerService.findBySurnameIgnoreCase(surnameToFilter));
        return "customerList";
    }


    @GetMapping("/customers/delete/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        customerService.deleteById(id);
        return "redirect:/customers";
    }


    @GetMapping("/customers/edit/{id}")
    public String loadEditPage(@PathVariable Long id, Model model) {
        Optional<Customer> customer = customerService.findById(id);

        if (customer.isPresent()) {
            model.addAttribute("customer", customer.get());
            return "editCustomer";
        }

        return "redirect:/customers";
    }


    @PostMapping("/customers/edit")
    public String updateCustomer(@Valid Customer customer, Errors errors) {
        if (errors.hasErrors()) {
            return "editCustomer";
        }

        customerService.save(customer);
        return "redirect:/customers";
    }
}