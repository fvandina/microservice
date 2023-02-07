package com.amigoscode.notification.customer;

import com.amigoscode.clients.fraud.FraudCheckResponse;
import com.amigoscode.clients.fraud.FraudClient;
import com.amigoscode.clients.notification.NotificationClient;
import com.amigoscode.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final NotificationClient notificationClient;

    public void registerCustomer(CustomerRegistrationRequest customerRegistrationRequest) {
        Customer customer = Customer.builder()
                .firstName(customerRegistrationRequest.firstName())
                .lastName(customerRegistrationRequest.lastName())
                .email(customerRegistrationRequest.email())
                .build();

        //todo: check if email valid
        //todo: check if email not taken
        customerRepository.saveAndFlush(customer);

        //todo: check if fraudster
        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("fraudster");
        }

        //todo: send notification
        // todo: make it async. i.e add to queue
        String sender = customer.getFirstName() + " " + customer.getLastName();
        notificationClient.sendNotification(
                new NotificationRequest(
                        customer.getId(),
                        sender,
                        customer.getEmail(),
                        String.format("Hi %s, welcome to Amigoscode...", sender)
                ));
    }
}
