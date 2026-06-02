package com.demo.gigforce.repository;

import com.demo.gigforce.entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

    // Get assignments for a contractor
    List<Assignment> findByContractorId(Long contractorId);

}