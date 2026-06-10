package com.demo.gigforce.service.impl;

import com.demo.gigforce.entity.Invoice;
import com.demo.gigforce.entity.Payment;
import com.demo.gigforce.repository.InvoiceRepository;
import com.demo.gigforce.repository.PaymentRepository;
import com.demo.gigforce.service.PaymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepo;

    @Autowired
    private InvoiceRepository invoiceRepo;

    @Override
    public Payment makePayment(Long invoiceId) {

        // ✅ STEP 1: Fetch Invoice
        Invoice inv = invoiceRepo.findById(invoiceId)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));

        // ✅ VALIDATION
        if (!"GENERATED".equalsIgnoreCase(inv.getStatus())) {
            throw new RuntimeException("Invoice must be GENERATED before payment");
        }

        // ✅ STEP 2: Create Payment
        Payment payment = new Payment();
        payment.setInvoiceId(invoiceId);
        payment.setAmount(inv.getAmount());
        payment.setStatus("PAID");
        payment.setPaymentDate(LocalDate.now());

        return paymentRepo.save(payment);
    }

    @Override
    public Payment getPayment(Long id) {
        return paymentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
    }
}