package com.demo.gigforce.entity;

import com.demo.gigforce.enums.*;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "resource_requisition")
public class ResourceRequisition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requisitionId;

    private Long hiringManagerId;
    private Long businessUnitId;
    private String skillRequired;

    @Enumerated(EnumType.STRING)
    private ExperienceLevel experienceLevel;

    @Enumerated(EnumType.STRING)
    private EngagementType engagementType;

    private LocalDate startDate;
    private Integer duration;
    private Double maxRate;

    @Enumerated(EnumType.STRING)
    private RequisitionStatus status;

    @OneToMany(mappedBy = "requisition", cascade = CascadeType.ALL)
    private List<VendorSubmission> submissions;

    // Default Constructor
    public ResourceRequisition() {}

    // Parameterized Constructor
    public ResourceRequisition(Long requisitionId, Long hiringManagerId, Long businessUnitId,
                               String skillRequired, ExperienceLevel experienceLevel,
                               EngagementType engagementType, LocalDate startDate,
                               Integer duration, Double maxRate, RequisitionStatus status) {
        this.requisitionId = requisitionId;
        this.hiringManagerId = hiringManagerId;
        this.businessUnitId = businessUnitId;
        this.skillRequired = skillRequired;
        this.experienceLevel = experienceLevel;
        this.engagementType = engagementType;
        this.startDate = startDate;
        this.duration = duration;
        this.maxRate = maxRate;
        this.status = status;
    }

    // Getters and Setters
    public Long getRequisitionId() { return requisitionId; }
    public void setRequisitionId(Long requisitionId) { this.requisitionId = requisitionId; }

    public Long getHiringManagerId() { return hiringManagerId; }
    public void setHiringManagerId(Long hiringManagerId) { this.hiringManagerId = hiringManagerId; }

    public Long getBusinessUnitId() { return businessUnitId; }
    public void setBusinessUnitId(Long businessUnitId) { this.businessUnitId = businessUnitId; }

    public String getSkillRequired() { return skillRequired; }
    public void setSkillRequired(String skillRequired) { this.skillRequired = skillRequired; }

    public ExperienceLevel getExperienceLevel() { return experienceLevel; }
    public void setExperienceLevel(ExperienceLevel experienceLevel) { this.experienceLevel = experienceLevel; }

    public EngagementType getEngagementType() { return engagementType; }
    public void setEngagementType(EngagementType engagementType) { this.engagementType = engagementType; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public Integer getDuration() { return duration; }
    public void setDuration(Integer duration) { this.duration = duration; }

    public Double getMaxRate() { return maxRate; }
    public void setMaxRate(Double maxRate) { this.maxRate = maxRate; }

    public RequisitionStatus getStatus() { return status; }
    public void setStatus(RequisitionStatus status) { this.status = status; }

    public List<VendorSubmission> getSubmissions() { return submissions; }
    public void setSubmissions(List<VendorSubmission> submissions) { this.submissions = submissions; }
}