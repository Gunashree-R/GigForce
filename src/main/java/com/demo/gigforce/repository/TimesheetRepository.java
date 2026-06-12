package com.demo.gigforce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.demo.gigforce.entity.Timesheet;

public interface TimesheetRepository extends JpaRepository<Timesheet, Long> {
}
