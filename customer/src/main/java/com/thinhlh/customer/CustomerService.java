package com.thinhlh.customer;

import com.thinhlh.clients.fraud.FraudCheckResponse;
import com.thinhlh.clients.fraud.FraudClient;
import com.thinhlh.clients.notification.NotificationClient;
import com.thinhlh.clients.notification.NotificationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RequiredArgsConstructor
@EnableFeignClients(
        basePackages = {
                "com.thinhlh.clients",
        }
)
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;
    private final FraudClient fraudClient;
    private final NotificationClient notificationClient;

    public void register(CustomerRequest customerRequest) {
        Customer customer = Customer.builder()
                .firstName(customerRequest.firstName())
                .lastname(customerRequest.lastName())
                .email(customerRequest.email())
                .build();

        // TODO: Check if fraudster
        customerRepository.saveAndFlush(customer);

        // Using Open Feign
        FraudCheckResponse response = fraudClient.isFraudster(customer.getId());

        if (response.isFraudster()) {
            throw new IllegalStateException("Fraudster!");
        }

        // TODO: Send notification

        // TODO: Send notification
        // 1. Use Rest Client
        // 2. Use Message Queue
        notificationClient.sendNotification(NotificationRequest
                .builder()
                .receiver(customer.getEmail())
                .message("Customer created")
                .build()
        );

        log.info("Customer created {}", customer);
    }

    private void registerWithRestTemplate(CustomerRequest customerRequest) {
        Customer customer = Customer.builder()
                .firstName(customerRequest.firstName())
                .lastname(customerRequest.lastName())
                .email(customerRequest.email())
                .build();

        // TODO: Check if fraudster
        customerRepository.saveAndFlush(customer);

        FraudCheckResponse response = restTemplate.getForObject(
                "http://FRAUD/api/v1/fraud/{customerId}",
                FraudCheckResponse.class,
                customer.getId()
        );

        if (response.isFraudster()) {
            throw new IllegalStateException("Fraudster!");
        }

        log.info("Customer created {}", customer);

        // TODO: Send notification
        // 1. Use Rest Client
        // 2. Use Message Queue
        notificationClient.sendNotification(NotificationRequest
                .builder()
                .receiver(customer.getEmail())
                .message("Customer created")
                .build()
        );
    }
}
