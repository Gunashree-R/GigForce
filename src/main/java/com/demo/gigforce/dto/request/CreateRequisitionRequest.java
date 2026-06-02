package com.demo.gigforce.dto.request;

import com.demo.gigforce.enums.*;

import java.time.LocalDate;

public class CreateRequisitionRequest {

    private Long hiringManagerId;
    private Long businessUnitId;
    private String skillRequired;
    private ExperienceLevel experienceLevel;
    private EngagementType engagementType;
    private LocalDate startDate;
    private Integer duration;
    private Double maxRate;

    public CreateRequisitionRequest() {}

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
}
