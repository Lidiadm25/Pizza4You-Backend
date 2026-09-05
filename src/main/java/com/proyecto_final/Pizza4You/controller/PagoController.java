package com.proyecto_final.Pizza4You.controller;


import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

// Sólo utilizado en la app android

@RestController
@RequestMapping("/pagos")
public class PagoController {

    @Value("${stripe.api.key}")
    private String apiKey;

    @PostMapping("/crear-intent")
    public Map<String, String> crearPaymentIntent(@RequestBody Map<String, Object> data) {
        Stripe.apiKey = apiKey;
        
        Integer monto = (Integer) data.get("monto");
        
        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount((long) monto)
                .setCurrency("eur")
                .setCaptureMethod(PaymentIntentCreateParams.CaptureMethod.MANUAL)
                .build();
                
        try {
            PaymentIntent intent = PaymentIntent.create(params);
            Map<String, String> responseData = new HashMap<>();
            responseData.put("id", intent.getId());
            responseData.put("clientSecret", intent.getClientSecret());
            return responseData;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
