package com.demo.gigforce.controller;

import com.demo.gigforce.dto.request.CreateRequisitionRequest;
import com.demo.gigforce.dto.response.RequisitionResponse;
import com.demo.gigforce.service.ResourceRequisitionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/requisitions")
public class ResourceRequisitionController {

    @Autowired
    private ResourceRequisitionService service;

    // Create Requisition
    @PostMapping
    public RequisitionResponse createRequisition(@RequestBody CreateRequisitionRequest request) {
        return service.createRequisition(request);
    }

    // Get All Requisitions
    @GetMapping
    public List<RequisitionResponse> getAllRequisitions() {
        return service.getAllRequisitions();
    }

    // Update Status
    @PutMapping("/{id}/status")
    public RequisitionResponse updateStatus(@PathVariable Long id,
                                            @RequestParam String status) {
        return service.updateStatus(id, status);
    }
}
