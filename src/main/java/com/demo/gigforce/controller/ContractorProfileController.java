package com.demo.gigforce.controller;

import com.demo.gigforce.entity.ContractorProfile;
import com.demo.gigforce.entity.EngagementHistory;
import com.demo.gigforce.service.ContractorProfileService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contractors")
public class ContractorProfileController {

    private final ContractorProfileService profileService;

    // Constructor
    public ContractorProfileController(ContractorProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping
    public ResponseEntity<ContractorProfile> createProfile(@RequestBody ContractorProfile profile) {
        return ResponseEntity.ok(profileService.createProfile(profile));
    }

    @GetMapping
    public ResponseEntity<List<ContractorProfile>> getAllProfiles() {
        return ResponseEntity.ok(profileService.getAllProfiles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContractorProfile> getProfileById(@PathVariable Long id) {

        Optional<ContractorProfile> profile = profileService.getProfileById(id);

        if (profile.isPresent()) {
            return ResponseEntity.ok(profile.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<ContractorProfile>> searchBySkill(@RequestParam String skill) {
        return ResponseEntity.ok(profileService.searchBySkill(skill));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContractorProfile> updateProfile(@PathVariable Long id,
                                                           @RequestBody ContractorProfile profile) {
        return ResponseEntity.ok(profileService.updateProfile(id, profile));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long id) {
        profileService.deleteProfile(id);
        return ResponseEntity.noContent().build();
    }

    // Engagement History
    @GetMapping("/{id}/engagement-history")
    public ResponseEntity<List<EngagementHistory>> getEngagementHistory(@PathVariable Long id) {
        return ResponseEntity.ok(profileService.getEngagementHistory(id));
    }

    @PostMapping("/{id}/engagement-history")
    public ResponseEntity<EngagementHistory> addEngagementHistory(
            @PathVariable Long id,
            @RequestBody EngagementHistory history) {

        history.setContractorId(id);

        return ResponseEntity.ok(profileService.addEngagementHistory(history));
    }
}
