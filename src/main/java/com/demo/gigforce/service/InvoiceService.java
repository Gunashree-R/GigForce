package com.demo.gigforce.service;

import com.demo.gigforce.entity.Invoice;


public interface InvoiceService {

    Invoice generateInvoice(Long timesheetId);

    Invoice getInvoice(Long id);
}