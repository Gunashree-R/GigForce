package com.demo.gigforce.repository;

import com.demo.gigforce.entity.TimesheetLine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TimesheetLineRepository extends JpaRepository<TimesheetLine, Long> {

    List<TimesheetLine> findByTimesheetId(Long timesheetId);
}
