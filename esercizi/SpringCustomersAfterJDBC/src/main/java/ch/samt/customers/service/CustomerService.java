package ch.samt.customers.service;

import ch.samt.customers.data.CustomerRepository;
import ch.samt.customers.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }


    public void save(Customer customer) {
        customerRepository.save(customer);
    }


    public List<Customer> findBySurnameIgnoreCase(String surname) {
        return customerRepository.findBySurnameIgnoreCase(surname);
    }


    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }


    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }
}