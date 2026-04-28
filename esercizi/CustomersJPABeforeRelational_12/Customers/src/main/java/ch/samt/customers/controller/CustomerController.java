package ch.samt.customers.controller;

import ch.samt.customers.domain.Customer;
import ch.samt.customers.service.CustomerService;
import ch.samt.customers.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/customers")
@Controller
public class CustomerController {

    private final CustomerService customerService;

    private final ReservationService reservationService;

    @Autowired
    public CustomerController(CustomerService customerService, ReservationService reservationService) {
        this.customerService = customerService;
        this.reservationService = reservationService;
    }

    @GetMapping
    public String loadCustomers(Model model) {
        model.addAttribute("customers", customerService.findAll());
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
        customerService.save(customer);
        return "redirect:/customers";
    }

    @GetMapping("/{surnameToFilter}")
    public String loadBySurname(Model model, @PathVariable String surnameToFilter) {
        List<Customer> filteredCustomers = customerService.findBySurnameIgnoreCase(surnameToFilter);
        model.addAttribute("customers", filteredCustomers);
        return "customerList";
    }

    @GetMapping("/under/{agelimit}")
    public String loadByAgeLimit(Model model, @PathVariable String agelimit) {
        List<Customer> filteredCustomers = customerService.findByAgeLessThan(Integer.valueOf(agelimit));
        model.addAttribute("customers", filteredCustomers);
        return "customerList";
    }

    @GetMapping("/reservations")
    public String loadReservations(Model model) {
        model.addAttribute("reservations", reservationService.findAll());
        return "reservationList";
    }

    @GetMapping("edit/{customerId}")
    public String loadEditPage(@ModelAttribute Customer customer, Model model,
                               @PathVariable long customerId) {
        Customer customerToEdit = customerService.findById(customerId);
        model.addAttribute("customer", customerToEdit);
        return "insertCustomer";
    }


    @PostMapping("/edit/{customerId}")
    public String updateCustomer(@Valid Customer customer, Errors errors,
                                 @PathVariable Long customerId) {
        customer.setId(customerId);
        if (errors.hasErrors()) {
            return "insertCustomer";
        }
        customerService.save(customer);
        return "redirect:/customers";
    }
}