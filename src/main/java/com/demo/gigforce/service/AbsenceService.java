package com.demo.gigforce.service;

import com.demo.gigforce.entity.Absence;

import java.util.List;

public interface AbsenceService {

    Absence requestLeave(Absence absence);
    List<Absence> getAll();
    Absence updateStatus(Long id, String status);
}
