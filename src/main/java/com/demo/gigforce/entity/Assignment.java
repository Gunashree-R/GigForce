package com.demo.gigforce.entity;

import com.demo.gigforce.enums.*;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "assignment")
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long assignmentId;

    @ManyToOne
    @JoinColumn(name = "requisition_id", nullable = false)
    private ResourceRequisition requisition;

    private Long contractorId;
    private Long hiringManagerId;
    private Long vendorId;

    private LocalDate startDate;
    private LocalDate endDate;
    private Double agreedRatePerDay;

    @Enumerated(EnumType.STRING)
    private EngagementType engagementType;

    private String sowReference;

    @Enumerated(EnumType.STRING)
    private AssignmentStatus status;

    @OneToMany(mappedBy = "assignment", cascade = CascadeType.ALL)
    private List<AssignmentAmendment> amendments;

    public Assignment() {}

    public Assignment(Long assignmentId, ResourceRequisition requisition, Long contractorId,
                      Long hiringManagerId, Long vendorId, LocalDate startDate,
                      LocalDate endDate, Double agreedRatePerDay,
                      EngagementType engagementType, String sowReference,
                      AssignmentStatus status) {
        this.assignmentId = assignmentId;
        this.requisition = requisition;
        this.contractorId = contractorId;
        this.hiringManagerId = hiringManagerId;
        this.vendorId = vendorId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.agreedRatePerDay = agreedRatePerDay;
        this.engagementType = engagementType;
        this.sowReference = sowReference;
        this.status = status;
    }

    public Long getAssignmentId() { return assignmentId; }
    public void setAssignmentId(Long assignmentId) { this.assignmentId = assignmentId; }

    public ResourceRequisition getRequisition() { return requisition; }
    public void setRequisition(ResourceRequisition requisition) { this.requisition = requisition; }

    public Long getContractorId() { return contractorId; }
    public void setContractorId(Long contractorId) { this.contractorId = contractorId; }

    public Long getHiringManagerId() { return hiringManagerId; }
    public void setHiringManagerId(Long hiringManagerId) { this.hiringManagerId = hiringManagerId; }

    public Long getVendorId() { return vendorId; }
    public void setVendorId(Long vendorId) { this.vendorId = vendorId; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public Double getAgreedRatePerDay() { return agreedRatePerDay; }
    public void setAgreedRatePerDay(Double agreedRatePerDay) { this.agreedRatePerDay = agreedRatePerDay; }

    public EngagementType getEngagementType() { return engagementType; }
    public void setEngagementType(EngagementType engagementType) { this.engagementType = engagementType; }

    public String getSowReference() { return sowReference; }
    public void setSowReference(String sowReference) { this.sowReference = sowReference; }

    public AssignmentStatus getStatus() { return status; }
    public void setStatus(AssignmentStatus status) { this.status = status; }

    public List<AssignmentAmendment> getAmendments() { return amendments; }
    public void setAmendments(List<AssignmentAmendment> amendments) { this.amendments = amendments; }
}
