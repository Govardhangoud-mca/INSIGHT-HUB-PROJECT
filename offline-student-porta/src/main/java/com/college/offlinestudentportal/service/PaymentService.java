package com.college.offlinestudentportal.service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {

    @Value("${razorpay.key.id}")
    private String razorpayKeyId;

    @Value("${razorpay.key.secret}")
    private String razorpayKeySecret;

    public Map<String, String> createOrder(int amount, String currency) throws Exception {
        try {
            RazorpayClient razorpay = new RazorpayClient(razorpayKeyId, razorpayKeySecret);
            JSONObject orderRequest = new JSONObject();
            orderRequest.put("amount", amount * 100); // Convert to paisa
            orderRequest.put("currency", currency);
            orderRequest.put("payment_capture", 1);

            Order order = razorpay.orders.create(orderRequest);

            Map<String, String> response = new HashMap<>();
            response.put("orderId", order.toJson().getString("id"));
            response.put("currency", order.toJson().getString("currency"));
            response.put("amount", String.valueOf(order.toJson().getInt("amount")));

            return response;
        } catch (Exception e) {
            throw new Exception("Error creating Razorpay order: " + e.getMessage());
        }
    }
}
