package com.demo.gigforce.controller;

import com.demo.gigforce.dto.request.SubmissionRequest;
import com.demo.gigforce.dto.response.SubmissionResponse;
import com.demo.gigforce.service.VendorSubmissionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/submissions")
public class VendorSubmissionController {

    @Autowired
    private VendorSubmissionService service;

    // Submit Candidate
    @PostMapping
    public SubmissionResponse submitProfile(@RequestBody SubmissionRequest request) {
        return service.submitProfile(request);
    }

    // Get submissions by requisition
    @GetMapping("/requisition/{requisitionId}")
    public List<SubmissionResponse> getByRequisition(@PathVariable Long requisitionId) {
        return service.getByRequisition(requisitionId);
    }

    // Update submission status
    @PutMapping("/{id}/status")
    public SubmissionResponse updateStatus(@PathVariable Long id,
                                           @RequestParam String status) {
        return service.updateStatus(id, status);
    }
}