package com.demo.gigforce.controller;

import com.demo.gigforce.entity.ContractorCertification;
import com.demo.gigforce.service.ContractorCertificationService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/certifications")
public class ContractorCertificationController {

    private final ContractorCertificationService certificationService;

    // Constructor
    public ContractorCertificationController(ContractorCertificationService certificationService) {
        this.certificationService = certificationService;
    }

    @PostMapping
    public ResponseEntity<ContractorCertification> addCertification(
            @RequestBody ContractorCertification cert) {

        return ResponseEntity.ok(certificationService.addCertification(cert));
    }

    @GetMapping("/contractor/{contractorId}")
    public ResponseEntity<List<ContractorCertification>> getByContractor(
            @PathVariable Long contractorId) {

        return ResponseEntity.ok(
                certificationService.getByContractorId(contractorId));
    }

    @PutMapping("/{certId}")
    public ResponseEntity<ContractorCertification> updateCertification(
            @PathVariable Long certId,
            @RequestBody ContractorCertification cert) {

        return ResponseEntity.ok(
                certificationService.updateCertification(certId, cert));
    }

    @DeleteMapping("/{certId}")
    public ResponseEntity<Void> deleteCertification(@PathVariable Long certId) {

        certificationService.deleteCertification(certId);

        return ResponseEntity.noContent().build();
    }
}