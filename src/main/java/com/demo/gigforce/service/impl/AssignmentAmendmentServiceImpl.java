package com.demo.gigforce.service.impl;

import com.demo.gigforce.dto.request.AmendmentRequest;
import com.demo.gigforce.entity.Assignment;
import com.demo.gigforce.entity.AssignmentAmendment;
import com.demo.gigforce.enums.AmendmentStatus;
import com.demo.gigforce.repository.AssignmentAmendmentRepository;
import com.demo.gigforce.repository.AssignmentRepository;
import com.demo.gigforce.service.AssignmentAmendmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssignmentAmendmentServiceImpl implements AssignmentAmendmentService {

    @Autowired
    private AssignmentRepository assignmentRepo;

    @Autowired
    private AssignmentAmendmentRepository amendmentRepo;

    @Override
    public String createAmendment(AmendmentRequest request) {

        Assignment assignment = assignmentRepo.findById(request.getAssignmentId())
                .orElseThrow(() -> new RuntimeException("Assignment not found"));

        AssignmentAmendment amendment = new AssignmentAmendment();
        amendment.setAssignment(assignment);
        amendment.setAmendmentType(request.getAmendmentType());
        amendment.setEffectiveDate(request.getEffectiveDate());
        amendment.setNewValue(request.getNewValue());
        amendment.setApprovedById(request.getApprovedById());
        amendment.setStatus(AmendmentStatus.PENDING);

        amendmentRepo.save(amendment);

        return "Amendment created successfully";
    }
}