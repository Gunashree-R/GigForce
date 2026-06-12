package com.demo.gigforce.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class TimesheetLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lineId;

    // ✅ LINK TO TIMESHEET
    private Long timesheetId;

    private LocalDate date;

    private double hoursWorked;

    private String activityDescription;

    private String status; // simple (can convert to enum later)

    // ✅ Getters & Setters

    public Long getLineId() { return lineId; }
    public void setLineId(Long lineId) { this.lineId = lineId; }

    public Long getTimesheetId() { return timesheetId; }
    public void setTimesheetId(Long timesheetId) { this.timesheetId = timesheetId; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public double getHoursWorked() { return hoursWorked; }
    public void setHoursWorked(double hoursWorked) { this.hoursWorked = hoursWorked; }

    public String getActivityDescription() { return activityDescription; }
    public void setActivityDescription(String activityDescription) {
        this.activityDescription = activityDescription;
    }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
