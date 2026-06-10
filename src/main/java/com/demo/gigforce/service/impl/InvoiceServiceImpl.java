package com.demo.gigforce.service.impl;

import com.demo.gigforce.entity.Absence;
import com.demo.gigforce.entity.Invoice;
import com.demo.gigforce.entity.Timesheet;
import com.demo.gigforce.repository.AbsenceRepository;
import com.demo.gigforce.repository.InvoiceRepository;
import com.demo.gigforce.repository.TimesheetRepository;
import com.demo.gigforce.service.InvoiceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepo;

    @Autowired
    private TimesheetRepository timesheetRepo;

    @Autowired
    private AbsenceRepository absenceRepo;

    @Override
    public Invoice generateInvoice(Long timesheetId) {

        // ✅ STEP 1: Fetch Timesheet
        Timesheet timesheet = timesheetRepo.findById(timesheetId)
                .orElseThrow(() -> new RuntimeException("Timesheet not found"));

        // ✅ STEP 2: VALIDATION (IMPORTANT)
        if (!"APPROVED".equalsIgnoreCase(timesheet.getStatus())) {
            throw new RuntimeException("Timesheet must be APPROVED before generating invoice");
        }

        // ✅ STEP 3: Fetch absences using YOUR repository method
        List<Absence> absences =
                absenceRepo.findByContractorIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                        timesheet.getContractorId(),
                        timesheet.getWeekEndDate(),   // ✅ IMPORTANT ORDER
                        timesheet.getWeekStartDate()
                );

        // ✅ STEP 4: Calculate total leave days (ONLY APPROVED)
        long totalLeaveDays = 0;

        for (Absence absence : absences) {

            if ("APPROVED".equalsIgnoreCase(absence.getStatus())) {

                long days = ChronoUnit.DAYS.between(
                        absence.getStartDate(),
                        absence.getEndDate()
                ) + 1;

                totalLeaveDays += days;
            }
        }

        // ✅ STEP 5: Convert leave days → hours
        double leaveHours = totalLeaveDays * 8;

        // ✅ STEP 6: Calculate final working hours
        double finalHours = timesheet.getTotalHours() - leaveHours;

        if (finalHours < 0) {
            finalHours = 0;
        }

        // ✅ STEP 7: Calculate amount
        double ratePerHour = 100;
        double amount = finalHours * ratePerHour;

        // ✅ STEP 8: Create Invoice
        Invoice invoice = new Invoice();
        invoice.setTimesheetId(timesheetId);
        invoice.setAmount(amount);
        invoice.setStatus("GENERATED");
        invoice.setGeneratedDate(LocalDate.now());

        return invoiceRepo.save(invoice);
    }

    @Override
    public Invoice getInvoice(Long id) {
        return invoiceRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));
    }
}