package com.demo.gigforce.controller;

import com.demo.gigforce.dto.request.CreateAssignmentRequest;
import com.demo.gigforce.dto.response.AssignmentResponse;
import com.demo.gigforce.service.AssignmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {

    @Autowired
    private AssignmentService service;

    // Create Assignment
    @PostMapping
    public AssignmentResponse createAssignment(@RequestBody CreateAssignmentRequest request) {
        return service.createAssignment(request);
    }

    // Terminate Assignment
    @PutMapping("/{id}/terminate")
    public AssignmentResponse terminateAssignment(@PathVariable Long id) {
        return service.terminateAssignment(id);
    }
}