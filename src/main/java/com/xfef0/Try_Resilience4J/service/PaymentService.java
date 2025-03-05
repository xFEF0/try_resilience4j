package com.xfef0.Try_Resilience4J.service;

import com.xfef0.Try_Resilience4J.manager.IPaymentManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PaymentService implements IPaymentService {

    private final IPaymentManager paymentManager;

    @Override
    public String processPayment() {
        return paymentManager.processPayment();
    }
}
