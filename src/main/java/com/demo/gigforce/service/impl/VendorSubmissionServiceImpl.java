package com.demo.gigforce.service.impl;

import com.demo.gigforce.dto.request.SubmissionRequest;
import com.demo.gigforce.dto.response.SubmissionResponse;
import com.demo.gigforce.entity.ResourceRequisition;
import com.demo.gigforce.entity.VendorSubmission;
import com.demo.gigforce.enums.SubmissionStatus;
import com.demo.gigforce.repository.ResourceRequisitionRepository;
import com.demo.gigforce.repository.VendorSubmissionRepository;
import com.demo.gigforce.service.VendorSubmissionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorSubmissionServiceImpl implements VendorSubmissionService {

    @Autowired
    private VendorSubmissionRepository submissionRepo;

    @Autowired
    private ResourceRequisitionRepository requisitionRepo;

    @Override
    public SubmissionResponse submitProfile(SubmissionRequest request) {

        ResourceRequisition requisition = requisitionRepo.findById(request.getRequisitionId())
                .orElseThrow(() -> new RuntimeException("Requisition not found"));

        VendorSubmission submission = new VendorSubmission();
        submission.setRequisition(requisition);
        submission.setVendorId(request.getVendorId());
        submission.setContractorId(request.getContractorId());
        submission.setProposedRate(request.getProposedRate());
        submission.setSubmissionDate(LocalDate.now());
        submission.setStatus(SubmissionStatus.SUBMITTED);

        return mapToResponse(submissionRepo.save(submission));
    }

    @Override
    public List<SubmissionResponse> getByRequisition(Long requisitionId) {
        return submissionRepo.findByRequisition_RequisitionId(requisitionId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public SubmissionResponse updateStatus(Long submissionId, String status) {

        VendorSubmission submission = submissionRepo.findById(submissionId)
                .orElseThrow(() -> new RuntimeException("Submission not found"));

        submission.setStatus(SubmissionStatus.valueOf(status));

        return mapToResponse(submissionRepo.save(submission));
    }

    private SubmissionResponse mapToResponse(VendorSubmission entity) {
        SubmissionResponse res = new SubmissionResponse();

        res.setSubmissionId(entity.getSubmissionId());
        res.setRequisitionId(entity.getRequisition().getRequisitionId());
        res.setVendorId(entity.getVendorId());
        res.setContractorId(entity.getContractorId());
        res.setProposedRate(entity.getProposedRate());
        res.setSubmissionDate(entity.getSubmissionDate());
        res.setStatus(entity.getStatus());

        return res;
    }
}
