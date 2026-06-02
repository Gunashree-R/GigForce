package com.demo.gigforce.service;

import com.demo.gigforce.dto.request.SubmissionRequest;
import com.demo.gigforce.dto.response.SubmissionResponse;

import java.util.List;

public interface VendorSubmissionService {

    SubmissionResponse submitProfile(SubmissionRequest request);

    List<SubmissionResponse> getByRequisition(Long requisitionId);

    SubmissionResponse updateStatus(Long submissionId, String status);
}
