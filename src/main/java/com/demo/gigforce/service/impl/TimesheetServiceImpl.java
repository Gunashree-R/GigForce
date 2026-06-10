package com.demo.gigforce.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.gigforce.entity.Timesheet;
import com.demo.gigforce.repository.TimesheetRepository;
import com.demo.gigforce.service.TimesheetService;

import java.time.LocalDate;

@Service
public class TimesheetServiceImpl implements TimesheetService {

    @Autowired
    private TimesheetRepository repository;

    @Override
    public Timesheet createTimesheet(Timesheet t) {

        // Business Logic
        t.setStatus("DRAFT");
        t.setSubmittedDate(LocalDate.now());

        return repository.save(t);
    }

    @Override
    public Timesheet submitTimesheet(Long id) {

        Timesheet t = repository.findById(id).orElseThrow();

        t.setStatus("SUBMITTED");
        t.setSubmittedDate(LocalDate.now());

        return repository.save(t);
    }

    @Override
    public Timesheet getTimesheet(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public Timesheet updateStatus(Long id, String status) {

        Timesheet t = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Timesheet not found"));

        // ✅ update status
        t.setStatus(status);

        // ✅ VERY IMPORTANT → save to DB
        return repository.save(t);
    }
}