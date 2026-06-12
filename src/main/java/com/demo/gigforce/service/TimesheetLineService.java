package com.demo.gigforce.service;

import com.demo.gigforce.entity.TimesheetLine;
import java.util.List;

public interface TimesheetLineService {

    TimesheetLine addLine(TimesheetLine line);

    List<TimesheetLine> getLinesByTimesheet(Long timesheetId);
}
