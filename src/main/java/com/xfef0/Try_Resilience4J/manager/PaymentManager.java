package com.xfef0.Try_Resilience4J.manager;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Component
public class PaymentManager implements IPaymentManager {

    private final RestTemplate restTemplate;

    @Override
    public String processPayment() {
        try {
            restTemplate.getForEntity("https://www.google.com", String.class);
            return "Payment processed";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
