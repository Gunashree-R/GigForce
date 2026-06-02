package com.demo.gigforce.entity;

import com.demo.gigforce.enums.SubmissionStatus;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "vendor_submission")
public class VendorSubmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long submissionId;

    @ManyToOne
    @JoinColumn(name = "requisition_id", nullable = false)
    private ResourceRequisition requisition;

    private Long vendorId;
    private Long contractorId;
    private Double proposedRate;
    private LocalDate submissionDate;

    @Enumerated(EnumType.STRING)
    private SubmissionStatus status;

    public VendorSubmission() {}

    public VendorSubmission(Long submissionId, ResourceRequisition requisition,
                            Long vendorId, Long contractorId,
                            Double proposedRate, LocalDate submissionDate,
                            SubmissionStatus status) {
        this.submissionId = submissionId;
        this.requisition = requisition;
        this.vendorId = vendorId;
        this.contractorId = contractorId;
        this.proposedRate = proposedRate;
        this.submissionDate = submissionDate;
        this.status = status;
    }

    public Long getSubmissionId() { return submissionId; }
    public void setSubmissionId(Long submissionId) { this.submissionId = submissionId; }

    public ResourceRequisition getRequisition() { return requisition; }
    public void setRequisition(ResourceRequisition requisition) { this.requisition = requisition; }

    public Long getVendorId() { return vendorId; }
    public void setVendorId(Long vendorId) { this.vendorId = vendorId; }

    public Long getContractorId() { return contractorId; }
    public void setContractorId(Long contractorId) { this.contractorId = contractorId; }

    public Double getProposedRate() { return proposedRate; }
    public void setProposedRate(Double proposedRate) { this.proposedRate = proposedRate; }

    public LocalDate getSubmissionDate() { return submissionDate; }
    public void setSubmissionDate(LocalDate submissionDate) { this.submissionDate = submissionDate; }

    public SubmissionStatus getStatus() { return status; }
    public void setStatus(SubmissionStatus status) { this.status = status; }
}