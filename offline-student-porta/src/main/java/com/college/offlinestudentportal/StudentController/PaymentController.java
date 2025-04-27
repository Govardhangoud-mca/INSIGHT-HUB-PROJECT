package com.college.offlinestudentportal.StudentController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.college.offlinestudentportal.service.PaymentService;

import java.util.Map;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create-order")
    public Map<String, String> createOrder(@RequestParam int amount, @RequestParam String currency) {
        try {
            return paymentService.createOrder(amount, currency);
        } catch (Exception e) {
            throw new RuntimeException("Payment creation failed");
        }
    }
}

