package com.demo.gigforce.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.gigforce.entity.Invoice;
import com.demo.gigforce.entity.Timesheet;
import com.demo.gigforce.repository.InvoiceRepository;
import com.demo.gigforce.repository.TimesheetRepository;
import com.demo.gigforce.service.InvoiceService;

import java.time.LocalDate;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepo;

    @Autowired
    private TimesheetRepository timesheetRepo;

    @Override
    public Invoice generateInvoice(Long timesheetId) {

        //  STEP 1: Fetch Timesheet
        Timesheet t = timesheetRepo.findById(timesheetId).orElseThrow();

        // STEP 2: Calculate amount
        double rate = 100; // fixed rate (can be dynamic later)
        double amount = t.getTotalHours() * rate;

        // STEP 3: Create Invoice
        Invoice invoice = new Invoice();
        invoice.setTimesheetId(timesheetId);
        invoice.setAmount(amount);
        invoice.setStatus("GENERATED");
        invoice.setGeneratedDate(LocalDate.now());

        // STEP 4: Save
        return invoiceRepo.save(invoice);
    }

    @Override
    public Invoice getInvoice(Long id) {
        return invoiceRepo.findById(id).orElseThrow();
    }
}