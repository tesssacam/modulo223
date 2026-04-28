package ch.samt.customers.service;

import ch.samt.customers.data.CustomerRepository;
import ch.samt.customers.domain.Customer;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public void save(@Valid Customer customer) {
        try {
            customerRepository.save(customer);
        } catch (RuntimeException ex) {
            throw new RuntimeException("Errore durante il save del customer in DB: " + customer, ex);
        }
    }

    public List<Customer> findBySurnameIgnoreCase(String surnameToFilter) {
        return customerRepository.findBySurnameIgnoreCase(surnameToFilter);
    }

    public List<Customer> findByAgeLessThan(Integer age) {
        return customerRepository.findByAgeLessThan(age);
    }

    public Customer findById(long customerId) {
        return customerRepository.findById(customerId).orElse(null);
    }
}
