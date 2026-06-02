package com.demo.gigforce.repository;

import com.demo.gigforce.entity.ContractorProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContractorProfileRepository extends JpaRepository<ContractorProfile, Long> {

    Optional<ContractorProfile> findByUserId(Long userId);

    List<ContractorProfile> findByPrimarySkillContainingIgnoreCase(String skill);
}
