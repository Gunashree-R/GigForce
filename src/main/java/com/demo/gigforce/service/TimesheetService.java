package com.demo.gigforce.service;

import com.demo.gigforce.entity.Timesheet;
import org.springframework.stereotype.Service;


public interface TimesheetService {

    Timesheet createTimesheet(Timesheet t);

    Timesheet submitTimesheet(Long id);

    Timesheet getTimesheet(Long id);
}