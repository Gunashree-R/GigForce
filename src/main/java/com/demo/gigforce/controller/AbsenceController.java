package com.demo.gigforce.controller;

import com.demo.gigforce.entity.Absence;
import com.demo.gigforce.service.AbsenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/absences")
public class AbsenceController {

    @Autowired
    private AbsenceService service;

    @PostMapping
    public Absence requestLeave(@RequestBody Absence absence) {
        return service.requestLeave(absence);
    }

    @GetMapping
    public List<Absence> getAll() {
        return service.getAll();
    }

    @PutMapping("/{id}")
    public Absence updateStatus(@PathVariable Long id,
                                @RequestParam String status) {
        return service.updateStatus(id, status);
    }
}
