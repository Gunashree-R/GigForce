package com.demo.gigforce.entity;

import com.demo.gigforce.enums.CertificationStatus;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "contractor_certifications")
public class ContractorCertification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long certId;

    private Long contractorId;

    private String certificationName;

    private String issuingBody;

    private LocalDate issuedDate;

    private LocalDate expiryDate;

    @Enumerated(EnumType.STRING)
    private CertificationStatus status;

    // Constructors
    public ContractorCertification() {}

    public ContractorCertification(Long certId, Long contractorId,
                                   String certificationName, String issuingBody,
                                   LocalDate issuedDate, LocalDate expiryDate,
                                   CertificationStatus status) {
        this.certId = certId;
        this.contractorId = contractorId;
        this.certificationName = certificationName;
        this.issuingBody = issuingBody;
        this.issuedDate = issuedDate;
        this.expiryDate = expiryDate;
        this.status = status;
    }

    // Getters & Setters
    public Long getCertId() { return certId; }
    public void setCertId(Long certId) { this.certId = certId; }

    public Long getContractorId() { return contractorId; }
    public void setContractorId(Long contractorId) { this.contractorId = contractorId; }

    public String getCertificationName() { return certificationName; }
    public void setCertificationName(String certificationName) { this.certificationName = certificationName; }

    public String getIssuingBody() { return issuingBody; }
    public void setIssuingBody(String issuingBody) { this.issuingBody = issuingBody; }

    public LocalDate getIssuedDate() { return issuedDate; }
    public void setIssuedDate(LocalDate issuedDate) { this.issuedDate = issuedDate; }

    public LocalDate getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDate expiryDate) { this.expiryDate = expiryDate; }

    public CertificationStatus getStatus() { return status; }
    public void setStatus(CertificationStatus status) { this.status = status; }
}
