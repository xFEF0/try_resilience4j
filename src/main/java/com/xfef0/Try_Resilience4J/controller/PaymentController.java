package com.xfef0.Try_Resilience4J.controller;

import com.xfef0.Try_Resilience4J.service.IPaymentService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PaymentController {

    private final IPaymentService paymentService;

    @PostMapping("/processPayment")
    @CircuitBreaker(name = "processPayment", fallbackMethod = "fallbackProcessPayment")
    public ResponseEntity<String> processPayment() {
        String result = paymentService.processPayment();
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<String> fallbackProcessPayment(Throwable throwable) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Sorry we cannot process your payment right now. Try again in a few minutes");
    }
}
