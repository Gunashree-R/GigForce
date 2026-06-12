package com.demo.gigforce.service.impl;

import com.demo.gigforce.entity.Absence;
import com.demo.gigforce.enums.AbsenceStatus;
import com.demo.gigforce.repository.AbsenceRepository;
import com.demo.gigforce.service.AbsenceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbsenceServiceImpl implements AbsenceService {

    @Autowired
    private AbsenceRepository absenceRepository;

    // 1. Request Leave
    @Override
    public Absence requestLeave(Absence absence) {

        // Default status when requesting leave
        absence.setStatus(AbsenceStatus.PENDING);

        return absenceRepository.save(absence);
    }

    // 2. Get All Absences
    @Override
    public List<Absence> getAll() {

        return absenceRepository.findAll();
    }

    // 3. Approve / Reject Leave
    @Override
    public Absence updateStatus(Long id, String status) {

        Absence absence = absenceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Absence not found with id: " + id));

        // Update status (APPROVED / REJECTED)
        //absence.setStatus(status);
        absence.setStatus(AbsenceStatus.valueOf(status.toUpperCase()));
        return absenceRepository.save(absence);
    }
}
