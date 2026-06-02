package com.demo.gigforce.service;

import com.demo.gigforce.entity.ContractorCertification;
import com.demo.gigforce.repository.ContractorCertificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContractorCertificationService {

    @Autowired
    private ContractorCertificationRepository certificationRepository;

    public ContractorCertification addCertification(ContractorCertification cert) {
        return certificationRepository.save(cert);
    }

    public List<ContractorCertification> getByContractorId(Long contractorId) {
        return certificationRepository.findByContractorId(contractorId);
    }

    public Optional<ContractorCertification> getById(Long certId) {
        return certificationRepository.findById(certId);
    }

    public ContractorCertification updateCertification(Long certId, ContractorCertification updated) {
        Optional<ContractorCertification> optional = certificationRepository.findById(certId);

        if (optional.isPresent()) {
            ContractorCertification cert = optional.get();
            cert.setCertificationName(updated.getCertificationName());
            cert.setIssuingBody(updated.getIssuingBody());
            cert.setIssuedDate(updated.getIssuedDate());
            cert.setExpiryDate(updated.getExpiryDate());
            cert.setStatus(updated.getStatus());
            return certificationRepository.save(cert);
        } else {
            throw new RuntimeException("Certification not found: " + certId);
        }
    }

    public void deleteCertification(Long certId) {
        certificationRepository.deleteById(certId);
    }
}