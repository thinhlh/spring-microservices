package com.thinhlh.customer;

import com.thinhlh.customer.Customer;
import com.thinhlh.customer.CustomerRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public record CustomerService(CustomerRepository customerRepository) {
    public void register(CustomerRequest customerRequest) {
        Customer customer = Customer.builder()
                .firstName(customerRequest.firstName())
                .lastname(customerRequest.lastName())
                .email(customerRequest.email())
                .build();

        customerRepository.save(customer);

        log.info("Customer created {}", customer);
    }
}
