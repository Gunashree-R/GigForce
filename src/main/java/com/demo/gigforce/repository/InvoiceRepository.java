package com.demo.gigforce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.demo.gigforce.entity.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}