package com.xfef0.Try_Resilience4J.controller;

import com.xfef0.Try_Resilience4J.service.IPaymentService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;

@RequiredArgsConstructor
@RestController
public class PaymentController {

    private static final Logger log = LoggerFactory.getLogger(PaymentController.class);
    private static final String PROCESS_PAYMENT = "processPayment";

    private final IPaymentService paymentService;

    @PostMapping("/processPayment")
    @CircuitBreaker(name = PROCESS_PAYMENT, fallbackMethod = "fallbackProcessPayment")
    @Retry(name = PROCESS_PAYMENT)
    public ResponseEntity<String> processPayment() {
        log.info("ProcessPayment invoked at {}", LocalTime.now());
        String result = paymentService.processPayment();
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<String> fallbackProcessPayment(Throwable throwable) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Sorry we cannot process your payment right now. Try again in a few minutes");
    }
}
