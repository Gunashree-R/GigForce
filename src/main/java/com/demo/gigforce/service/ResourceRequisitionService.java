package com.demo.gigforce.service;

import com.demo.gigforce.dto.request.CreateRequisitionRequest;
import com.demo.gigforce.dto.response.RequisitionResponse;

import java.util.List;

public interface ResourceRequisitionService {

    RequisitionResponse createRequisition(CreateRequisitionRequest request);

    List<RequisitionResponse> getAllRequisitions();

    RequisitionResponse updateStatus(Long requisitionId, String status);
}