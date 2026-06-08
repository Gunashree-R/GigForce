package com.demo.gigforce.service;

import com.demo.gigforce.entity.Payment;
import org.springframework.stereotype.Service;


public interface PaymentService {

    Payment makePayment(Long invoiceId);

    Payment getPayment(Long id);
}