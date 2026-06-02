package com.demo.gigforce.service;

import com.demo.gigforce.entity.*;
import com.demo.gigforce.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContractorProfileService {

    @Autowired
    private ContractorProfileRepository profileRepository;

    @Autowired
    private ContractorCertificationRepository certificationRepository;

    @Autowired
    private EngagementHistoryRepository engagementHistoryRepository;

    public ContractorProfile createProfile(ContractorProfile profile) {
        return profileRepository.save(profile);
    }

    public List<ContractorProfile> getAllProfiles() {
        return profileRepository.findAll();
    }

    public Optional<ContractorProfile> getProfileById(Long id) {
        return profileRepository.findById(id);
    }

    public Optional<ContractorProfile> getProfileByUserId(Long userId) {
        return profileRepository.findByUserId(userId);
    }

    public List<ContractorProfile> searchBySkill(String skill) {
        return profileRepository.findByPrimarySkillContainingIgnoreCase(skill);
    }

    public ContractorProfile updateProfile(Long id, ContractorProfile updated) {
        Optional<ContractorProfile> optional = profileRepository.findById(id);

        if (optional.isPresent()) {
            ContractorProfile p = optional.get();
            p.setDisplayName(updated.getDisplayName());
            p.setPrimarySkill(updated.getPrimarySkill());
            p.setSkillTags(updated.getSkillTags());
            p.setExperienceYears(updated.getExperienceYears());
            p.setPreferredEngagementType(updated.getPreferredEngagementType());
            p.setAvailabilityStatus(updated.getAvailabilityStatus());
            p.setStatus(updated.getStatus());
            return profileRepository.save(p);
        } else {
            throw new RuntimeException("Profile not found: " + id);
        }
    }

    public void deleteProfile(Long id) {
        profileRepository.deleteById(id);
    }

    // Certifications
    public List<ContractorCertification> getCertificationsByContractor(Long contractorId) {
        return certificationRepository.findByContractorId(contractorId);
    }

    // Engagement History
    public List<EngagementHistory> getEngagementHistory(Long contractorId) {
        return engagementHistoryRepository.findByContractorId(contractorId);
    }

    public EngagementHistory addEngagementHistory(EngagementHistory history) {
        return engagementHistoryRepository.save(history);
    }
}