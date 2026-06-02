package com.demo.gigforce.repository;

import com.demo.gigforce.entity.ResourceRequisition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceRequisitionRepository extends JpaRepository<ResourceRequisition, Long> {

}
