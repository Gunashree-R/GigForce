package com.demo.gigforce.controller;

import com.demo.gigforce.entity.TimesheetLine;
import com.demo.gigforce.service.TimesheetLineService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/timesheet-lines")
public class TimesheetLineController {

    @Autowired
    private TimesheetLineService service;

    // Add daily entry
    @PostMapping
    public TimesheetLine add(@RequestBody TimesheetLine line) {
        return service.addLine(line);
    }

    // Get all lines for a timesheet
    @GetMapping("/{timesheetId}")
    public List<TimesheetLine> getLines(@PathVariable Long timesheetId) {
        return service.getLinesByTimesheet(timesheetId);
    }
}
