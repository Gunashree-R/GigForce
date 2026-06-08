package com.demo.gigforce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.demo.gigforce.entity.Payment;
import com.demo.gigforce.service.PaymentService;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private PaymentService service;

    //  Make Payment
    @PostMapping("/{invoiceId}")
    public Payment makePayment(@PathVariable Long invoiceId) {
        return service.makePayment(invoiceId);
    }

    // Get Payment
    @GetMapping("/{id}")
    public Payment getPayment(@PathVariable Long id) {
        return service.getPayment(id);
    }
}