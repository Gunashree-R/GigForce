package com.demo.gigforce.service.impl;

import com.demo.gigforce.dto.request.CreateRequisitionRequest;
import com.demo.gigforce.dto.response.RequisitionResponse;
import com.demo.gigforce.entity.ResourceRequisition;
import com.demo.gigforce.enums.RequisitionStatus;
import com.demo.gigforce.repository.ResourceRequisitionRepository;
import com.demo.gigforce.service.ResourceRequisitionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResourceRequisitionServiceImpl implements ResourceRequisitionService {

    @Autowired
    private ResourceRequisitionRepository repository;

    @Override
    public RequisitionResponse createRequisition(CreateRequisitionRequest request) {

        ResourceRequisition entity = new ResourceRequisition();
        entity.setHiringManagerId(request.getHiringManagerId());
        entity.setBusinessUnitId(request.getBusinessUnitId());
        entity.setSkillRequired(request.getSkillRequired());
        entity.setExperienceLevel(request.getExperienceLevel());
        entity.setEngagementType(request.getEngagementType());
        entity.setStartDate(request.getStartDate());
        entity.setDuration(request.getDuration());
        entity.setMaxRate(request.getMaxRate());

        entity.setStatus(RequisitionStatus.DRAFT);

        ResourceRequisition saved = repository.save(entity);

        return mapToResponse(saved);
    }

    @Override
    public List<RequisitionResponse> getAllRequisitions() {
        return repository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public RequisitionResponse updateStatus(Long requisitionId, String status) {

        ResourceRequisition entity = repository.findById(requisitionId)
                .orElseThrow(() -> new RuntimeException("Requisition not found"));

        entity.setStatus(RequisitionStatus.valueOf(status));

        return mapToResponse(repository.save(entity));
    }

    private RequisitionResponse mapToResponse(ResourceRequisition entity) {
        RequisitionResponse res = new RequisitionResponse();

        res.setRequisitionId(entity.getRequisitionId());
        res.setSkillRequired(entity.getSkillRequired());
        res.setExperienceLevel(entity.getExperienceLevel());
        res.setEngagementType(entity.getEngagementType());
        res.setStartDate(entity.getStartDate());
        res.setDuration(entity.getDuration());
        res.setMaxRate(entity.getMaxRate());
        res.setStatus(entity.getStatus());

        return res;
    }
}
