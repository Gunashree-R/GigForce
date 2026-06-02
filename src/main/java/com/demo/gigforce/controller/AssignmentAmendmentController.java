package com.demo.gigforce.controller;

import com.demo.gigforce.dto.request.AmendmentRequest;
import com.demo.gigforce.service.AssignmentAmendmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/amendments")
public class AssignmentAmendmentController {

    @Autowired
    private AssignmentAmendmentService service;

    // Create Amendment
    @PostMapping
    public String createAmendment(@RequestBody AmendmentRequest request) {
        return service.createAmendment(request);
    }
}
