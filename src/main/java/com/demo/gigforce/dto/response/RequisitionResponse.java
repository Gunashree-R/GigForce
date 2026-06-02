package com.demo.gigforce.dto.response;

import com.demo.gigforce.enums.*;

import java.time.LocalDate;

public class RequisitionResponse {

    private Long requisitionId;
    private String skillRequired;
    private ExperienceLevel experienceLevel;
    private EngagementType engagementType;
    private LocalDate startDate;
    private Integer duration;
    private Double maxRate;
    private RequisitionStatus status;

    public RequisitionResponse() {}

    public Long getRequisitionId() { return requisitionId; }
    public void setRequisitionId(Long requisitionId) { this.requisitionId = requisitionId; }

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
}
