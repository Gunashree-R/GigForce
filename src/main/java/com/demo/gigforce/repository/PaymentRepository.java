package com.demo.gigforce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.demo.gigforce.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}