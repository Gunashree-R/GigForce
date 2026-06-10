package com.demo.gigforce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.demo.gigforce.entity.Timesheet;
import com.demo.gigforce.service.TimesheetService;

@RestController
@RequestMapping("/api/timesheets")
public class TimesheetController {

    @Autowired
    private TimesheetService service;

    //  Create Timesheet
    @PostMapping
    public Timesheet create(@RequestBody Timesheet t) {
        return service.createTimesheet(t);
    }

    // Submit Timesheet
    @PutMapping("/{id}/submit")
    public Timesheet submit(@PathVariable Long id) {
        return service.submitTimesheet(id);
    }

    // Get Timesheet
    @GetMapping("/{id}")
    public Timesheet get(@PathVariable Long id) {
        return service.getTimesheet(id);
    }

    @PutMapping("/{id}/status")
    public Timesheet updateStatus(@PathVariable Long id,
                                  @RequestParam String status) {
        return service.updateStatus(id, status);
    }
}