package com.demo.gigforce.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "engagement_history")
public class EngagementHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long historyId;

    private Long contractorId;

    private String clientName;

    private String role;

    private LocalDate startDate;

    private LocalDate endDate;

    private Double performanceRating;

    private String feedbackSummary;

    // Constructors
    public EngagementHistory() {}

    public EngagementHistory(Long historyId, Long contractorId, String clientName,
                             String role, LocalDate startDate, LocalDate endDate,
                             Double performanceRating, String feedbackSummary) {
        this.historyId = historyId;
        this.contractorId = contractorId;
        this.clientName = clientName;
        this.role = role;
        this.startDate = startDate;
        this.endDate = endDate;
        this.performanceRating = performanceRating;
        this.feedbackSummary = feedbackSummary;
    }

    // Getters & Setters
    public Long getHistoryId() { return historyId; }
    public void setHistoryId(Long historyId) { this.historyId = historyId; }

    public Long getContractorId() { return contractorId; }
    public void setContractorId(Long contractorId) { this.contractorId = contractorId; }

    public String getClientName() { return clientName; }
    public void setClientName(String clientName) { this.clientName = clientName; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public Double getPerformanceRating() { return performanceRating; }
    public void setPerformanceRating(Double performanceRating) { this.performanceRating = performanceRating; }

    public String getFeedbackSummary() { return feedbackSummary; }
    public void setFeedbackSummary(String feedbackSummary) { this.feedbackSummary = feedbackSummary; }
}