package com.demo.gigforce.entity;

import com.demo.gigforce.enums.TimesheetStatus;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "timesheet")
public class Timesheet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long timesheetId;

    private Long assignmentId;
    private Long contractorId;

    private LocalDate weekStartDate;
    private LocalDate weekEndDate;

    private double totalHours;
    private double overtimeHours;

    private LocalDate submittedDate;

    @Enumerated(EnumType.STRING)
    private TimesheetStatus status;

    // ✅ Getters & Setters

    public Long getTimesheetId() { return timesheetId; }
    public void setTimesheetId(Long timesheetId) { this.timesheetId = timesheetId; }

    public Long getAssignmentId() { return assignmentId; }
    public void setAssignmentId(Long assignmentId) { this.assignmentId = assignmentId; }

    public Long getContractorId() { return contractorId; }
    public void setContractorId(Long contractorId) { this.contractorId = contractorId; }

    public LocalDate getWeekStartDate() { return weekStartDate; }
    public void setWeekStartDate(LocalDate weekStartDate) { this.weekStartDate = weekStartDate; }

    public LocalDate getWeekEndDate() { return weekEndDate; }
    public void setWeekEndDate(LocalDate weekEndDate) { this.weekEndDate = weekEndDate; }

    public double getTotalHours() { return totalHours; }
    public void setTotalHours(double totalHours) { this.totalHours = totalHours; }

    public double getOvertimeHours() { return overtimeHours; }
    public void setOvertimeHours(double overtimeHours) { this.overtimeHours = overtimeHours; }

    public LocalDate getSubmittedDate() { return submittedDate; }
    public void setSubmittedDate(LocalDate submittedDate) { this.submittedDate = submittedDate; }

    public TimesheetStatus getStatus() { return status; }
    public void setStatus(TimesheetStatus status) { this.status = status; }
}