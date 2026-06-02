package com.demo.gigforce.service.impl;

import com.demo.gigforce.dto.request.CreateAssignmentRequest;
import com.demo.gigforce.dto.response.AssignmentResponse;
import com.demo.gigforce.entity.Assignment;
import com.demo.gigforce.entity.ResourceRequisition;
import com.demo.gigforce.enums.AssignmentStatus;
import com.demo.gigforce.repository.AssignmentRepository;
import com.demo.gigforce.repository.ResourceRequisitionRepository;
import com.demo.gigforce.service.AssignmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssignmentServiceImpl implements AssignmentService {

    @Autowired
    private AssignmentRepository assignmentRepo;

    @Autowired
    private ResourceRequisitionRepository requisitionRepo;

    @Override
    public AssignmentResponse createAssignment(CreateAssignmentRequest request) {

        ResourceRequisition requisition = requisitionRepo.findById(request.getRequisitionId())
                .orElseThrow(() -> new RuntimeException("Requisition not found"));

        Assignment assignment = new Assignment();
        assignment.setRequisition(requisition);
        assignment.setContractorId(request.getContractorId());
        assignment.setVendorId(request.getVendorId());
        assignment.setHiringManagerId(request.getHiringManagerId());
        assignment.setStartDate(request.getStartDate());
        assignment.setEndDate(request.getEndDate());
        assignment.setAgreedRatePerDay(request.getAgreedRatePerDay());
        assignment.setEngagementType(request.getEngagementType());
        assignment.setSowReference(request.getSowReference());
        assignment.setStatus(AssignmentStatus.ACTIVE);

        return mapToResponse(assignmentRepo.save(assignment));
    }

    @Override
    public AssignmentResponse terminateAssignment(Long assignmentId) {

        Assignment assignment = assignmentRepo.findById(assignmentId)
                .orElseThrow(() -> new RuntimeException("Assignment not found"));

        assignment.setStatus(AssignmentStatus.TERMINATED_EARLY);

        return mapToResponse(assignmentRepo.save(assignment));
    }

    private AssignmentResponse mapToResponse(Assignment entity) {
        AssignmentResponse res = new AssignmentResponse();

        res.setAssignmentId(entity.getAssignmentId());
        res.setRequisitionId(entity.getRequisition().getRequisitionId());
        res.setContractorId(entity.getContractorId());
        res.setVendorId(entity.getVendorId());
        res.setStartDate(entity.getStartDate());
        res.setEndDate(entity.getEndDate());
        res.setAgreedRatePerDay(entity.getAgreedRatePerDay());
        res.setEngagementType(entity.getEngagementType());
        res.setSowReference(entity.getSowReference());
        res.setStatus(entity.getStatus());

        return res;
    }
}