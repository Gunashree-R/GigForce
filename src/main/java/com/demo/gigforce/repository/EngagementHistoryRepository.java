package com.demo.gigforce.repository;

import com.demo.gigforce.entity.EngagementHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EngagementHistoryRepository extends JpaRepository<EngagementHistory, Long> {

    List<EngagementHistory> findByContractorId(Long contractorId);
}