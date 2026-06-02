package com.demo.gigforce.service;

import com.demo.gigforce.dto.request.CreateAssignmentRequest;
import com.demo.gigforce.dto.response.AssignmentResponse;

public interface AssignmentService {

    AssignmentResponse createAssignment(CreateAssignmentRequest request);

    AssignmentResponse terminateAssignment(Long assignmentId);
}
