package com.dynatrace.bidding.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class BiddingSeries {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String seriesName; // e.g., "FF"
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String status; // e.g., "ACTIVE", "ENDED"

    // Constructors, Getters, and Setters
    public BiddingSeries() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getSeriesName() { return seriesName; }
    public void setSeriesName(String seriesName) { this.seriesName = seriesName; }
    public LocalDateTime getStartDate() { return startDate; }
    public void setStartDate(LocalDateTime startDate) { this.startDate = startDate; }
    public LocalDateTime getEndDate() { return endDate; }
    public void setEndDate(LocalDateTime endDate) { this.endDate = endDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public BiddingSeries(Long id, String seriesName, LocalDateTime startDate, LocalDateTime endDate, String status) {
        this.id = id;
        this.seriesName = seriesName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    @Override
    public String toString() {
        return "BiddingSeries{" +
                "id=" + id +
                ", seriesName='" + seriesName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status='" + status + '\'' +
                '}';
    }
}
