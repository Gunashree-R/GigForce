package com.demo.gigforce.repository;

import com.demo.gigforce.entity.ContractorCertification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractorCertificationRepository extends JpaRepository<ContractorCertification, Long> {

    List<ContractorCertification> findByContractorId(Long contractorId);
}