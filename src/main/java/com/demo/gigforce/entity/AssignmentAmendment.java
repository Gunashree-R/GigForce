package com.demo.gigforce.entity;

import com.demo.gigforce.enums.*;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "assignment_amendment")
public class AssignmentAmendment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long amendmentId;

    @ManyToOne
    @JoinColumn(name = "assignment_id", nullable = false)
    private Assignment assignment;

    @Enumerated(EnumType.STRING)
    private AmendmentType amendmentType;

    private LocalDate effectiveDate;
    private String newValue;
    private Long approvedById;

    @Enumerated(EnumType.STRING)
    private AmendmentStatus status;

    public AssignmentAmendment() {}

    public AssignmentAmendment(Long amendmentId, Assignment assignment,
                               AmendmentType amendmentType, LocalDate effectiveDate,
                               String newValue, Long approvedById,
                               AmendmentStatus status) {
        this.amendmentId = amendmentId;
        this.assignment = assignment;
        this.amendmentType = amendmentType;
        this.effectiveDate = effectiveDate;
        this.newValue = newValue;
        this.approvedById = approvedById;
        this.status = status;
    }

    public Long getAmendmentId() { return amendmentId; }
    public void setAmendmentId(Long amendmentId) { this.amendmentId = amendmentId; }

    public Assignment getAssignment() { return assignment; }
    public void setAssignment(Assignment assignment) { this.assignment = assignment; }

    public AmendmentType getAmendmentType() { return amendmentType; }
    public void setAmendmentType(AmendmentType amendmentType) { this.amendmentType = amendmentType; }

    public LocalDate getEffectiveDate() { return effectiveDate; }
    public void setEffectiveDate(LocalDate effectiveDate) { this.effectiveDate = effectiveDate; }

    public String getNewValue() { return newValue; }
    public void setNewValue(String newValue) { this.newValue = newValue; }

    public Long getApprovedById() { return approvedById; }
    public void setApprovedById(Long approvedById) { this.approvedById = approvedById; }

    public AmendmentStatus getStatus() { return status; }
    public void setStatus(AmendmentStatus status) { this.status = status; }
}