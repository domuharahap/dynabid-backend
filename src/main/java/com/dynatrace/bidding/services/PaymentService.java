package com.dynatrace.bidding.services;

import com.dynatrace.bidding.model.Payment;
import com.dynatrace.bidding.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment processPayment(Payment payment) {
        // Here you would integrate with a real payment gateway (e.g., Stripe, PayPal).
        // For this demo, we'll simulate a successful payment.
        payment.setStatus("COMPLETED");
        return paymentRepository.save(payment);
    }

}
