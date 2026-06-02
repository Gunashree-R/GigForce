package com.demo.gigforce.repository;

import com.demo.gigforce.entity.AssignmentAmendment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentAmendmentRepository extends JpaRepository<AssignmentAmendment, Long> {

    // Get all amendments for an assignment
    List<AssignmentAmendment> findByAssignment_AssignmentId(Long assignmentId);

}