package com.demo.gigforce.dto.request;

import com.demo.gigforce.enums.EngagementType;

import java.time.LocalDate;

public class CreateAssignmentRequest {

    private Long requisitionId;
    private Long contractorId;
    private Long vendorId;
    private Long hiringManagerId;

    private LocalDate startDate;
    private LocalDate endDate;

    private Double agreedRatePerDay;
    private EngagementType engagementType;

    private String sowReference;

    public CreateAssignmentRequest() {}

    public Long getRequisitionId() { return requisitionId; }
    public void setRequisitionId(Long requisitionId) { this.requisitionId = requisitionId; }

    public Long getContractorId() { return contractorId; }
    public void setContractorId(Long contractorId) { this.contractorId = contractorId; }

    public Long getVendorId() { return vendorId; }
    public void setVendorId(Long vendorId) { this.vendorId = vendorId; }

    public Long getHiringManagerId() { return hiringManagerId; }
    public void setHiringManagerId(Long hiringManagerId) { this.hiringManagerId = hiringManagerId; }

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
}
