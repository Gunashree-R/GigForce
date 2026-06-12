package com.demo.gigforce.service.impl;

import com.demo.gigforce.entity.TimesheetLine;
import com.demo.gigforce.repository.TimesheetLineRepository;
import com.demo.gigforce.service.TimesheetLineService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimesheetLineServiceImpl implements TimesheetLineService {

    @Autowired
    private TimesheetLineRepository repo;

    @Override
    public TimesheetLine addLine(TimesheetLine line) {

        // default status
        line.setStatus("SUBMITTED");

        return repo.save(line);
    }

    @Override
    public List<TimesheetLine> getLinesByTimesheet(Long timesheetId) {
        return repo.findByTimesheetId(timesheetId);
    }
}
