package com.demo.gigforce.repository;

import com.demo.gigforce.entity.Absence;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AbsenceRepository extends JpaRepository<Absence, Long> {

    List<Absence> findByContractorIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            Long contractorId,
            LocalDate weekEndDate,
            LocalDate weekStartDate
    );
}
