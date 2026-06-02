package com.demo.gigforce.repository;

import com.demo.gigforce.entity.VendorSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendorSubmissionRepository extends JpaRepository<VendorSubmission, Long> {

    // Get all submissions for a requisition
    List<VendorSubmission> findByRequisition_RequisitionId(Long requisitionId);

}
