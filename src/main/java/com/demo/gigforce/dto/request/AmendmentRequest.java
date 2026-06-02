package com.demo.gigforce.dto.request;

import com.demo.gigforce.enums.AmendmentType;

import java.time.LocalDate;

public class AmendmentRequest {

    private Long assignmentId;
    private AmendmentType amendmentType;
    private LocalDate effectiveDate;
    private String newValue;
    private Long approvedById;

    public AmendmentRequest() {}

    public Long getAssignmentId() { return assignmentId; }
    public void setAssignmentId(Long assignmentId) { this.assignmentId = assignmentId; }

    public AmendmentType getAmendmentType() { return amendmentType; }
    public void setAmendmentType(AmendmentType amendmentType) { this.amendmentType = amendmentType; }

    public LocalDate getEffectiveDate() { return effectiveDate; }
    public void setEffectiveDate(LocalDate effectiveDate) { this.effectiveDate = effectiveDate; }

    public String getNewValue() { return newValue; }
    public void setNewValue(String newValue) { this.newValue = newValue; }

    public Long getApprovedById() { return approvedById; }
    public void setApprovedById(Long approvedById) { this.approvedById = approvedById; }
}