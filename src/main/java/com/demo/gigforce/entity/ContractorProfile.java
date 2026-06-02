package com.demo.gigforce.entity;

import com.demo.gigforce.enums.*;
import jakarta.persistence.*;

@Entity
@Table(name = "contractor_profiles")
public class ContractorProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contractorId;

    private Long userId;

    private String displayName;

    private String primarySkill;

    private String skillTags;

    private Integer experienceYears;

    @Enumerated(EnumType.STRING)
    private EngagementType preferredEngagementType;

    @Enumerated(EnumType.STRING)
    private AvailabilityStatus availabilityStatus;

    @Enumerated(EnumType.STRING)
    private ContractorStatus status;

    // Constructors
    public ContractorProfile() {}

    public ContractorProfile(Long contractorId, Long userId, String displayName,
                             String primarySkill, String skillTags,
                             Integer experienceYears,
                             EngagementType preferredEngagementType,
                             AvailabilityStatus availabilityStatus,
                             ContractorStatus status) {
        this.contractorId = contractorId;
        this.userId = userId;
        this.displayName = displayName;
        this.primarySkill = primarySkill;
        this.skillTags = skillTags;
        this.experienceYears = experienceYears;
        this.preferredEngagementType = preferredEngagementType;
        this.availabilityStatus = availabilityStatus;
        this.status = status;
    }

    // Getters & Setters
    public Long getContractorId() { return contractorId; }
    public void setContractorId(Long contractorId) { this.contractorId = contractorId; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getDisplayName() { return displayName; }
    public void setDisplayName(String displayName) { this.displayName = displayName; }

    public String getPrimarySkill() { return primarySkill; }
    public void setPrimarySkill(String primarySkill) { this.primarySkill = primarySkill; }

    public String getSkillTags() { return skillTags; }
    public void setSkillTags(String skillTags) { this.skillTags = skillTags; }

    public Integer getExperienceYears() { return experienceYears; }
    public void setExperienceYears(Integer experienceYears) { this.experienceYears = experienceYears; }

    public EngagementType getPreferredEngagementType() { return preferredEngagementType; }
    public void setPreferredEngagementType(EngagementType preferredEngagementType) {
        this.preferredEngagementType = preferredEngagementType;
    }

    public AvailabilityStatus getAvailabilityStatus() { return availabilityStatus; }
    public void setAvailabilityStatus(AvailabilityStatus availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    public ContractorStatus getStatus() { return status; }
    public void setStatus(ContractorStatus status) { this.status = status; }
}