package com.vutheduyet.exercise03.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.vutheduyet.exercise03.entity.Customer;
import com.vutheduyet.exercise03.repository.CustomerRepository;
import com.vutheduyet.exercise03.service.CustomerService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer getCustomerById(String customerId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        return optionalCustomer.orElse(null);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        Optional<Customer> optionalExistingCustomer = customerRepository.findById(customer.getCustomerId());
        if (optionalExistingCustomer.isPresent()) {
            Customer existingCustomer = optionalExistingCustomer.get();
            existingCustomer.setFirstName(customer.getFirstName());
            existingCustomer.setLastName(customer.getLastName());
            existingCustomer.setPhoneNumber(customer.getPhoneNumber());
            existingCustomer.setEmail(customer.getEmail());
            existingCustomer.setPasswordHash(customer.getPasswordHash());
            existingCustomer.setActive(customer.isActive());
            existingCustomer.setRegisteredAt(customer.getRegisteredAt());

            Customer updatedCustomer = customerRepository.save(existingCustomer);
            return updatedCustomer;
        }

        return null;
    }

    @Override
    public void deleteCustomer(String customerId) {
        customerRepository.deleteById(customerId);
    }
}
