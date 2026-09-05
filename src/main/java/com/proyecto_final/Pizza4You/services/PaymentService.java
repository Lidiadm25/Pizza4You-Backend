package com.proyecto_final.Pizza4You.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;

@Service
public class PaymentService {

    @Value("${stripe.api.key}")
    private String apiKey;

    public void capturarPago(String paymentIntentId) {
        Stripe.apiKey = apiKey;
        try {
            PaymentIntent intent = PaymentIntent.retrieve(paymentIntentId);
            intent.capture();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void cancelarPago(String paymentIntentId) {
        Stripe.apiKey = apiKey;
        try {
            PaymentIntent intent = PaymentIntent.retrieve(paymentIntentId);
            String status = intent.getStatus();
            if (!"succeeded".equals(status) && !"canceled".equals(status)) {
                intent.cancel();
            }
        } catch (Exception e) {
            System.err.println("Stripe error: " + e.getMessage());
        }
    }
}