package com.demo.gigforce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.demo.gigforce.entity.Invoice;
import com.demo.gigforce.service.InvoiceService;

@RestController
@RequestMapping("/api/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService service;

    //  Generate Invoice
    @PostMapping("/{timesheetId}")
    public Invoice generate(@PathVariable Long timesheetId) {
        return service.generateInvoice(timesheetId);
    }

    //  Get Invoice
    @GetMapping("/{id}")
    public Invoice get(@PathVariable Long id) {
        return service.getInvoice(id);
    }
}