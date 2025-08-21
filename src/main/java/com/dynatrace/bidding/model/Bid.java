package com.dynatrace.bidding.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Bid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String biddingNumber;
    private double amount;
    private LocalDateTime timestamp;

    private String status;

    // Constructors, Getters, and Setters
    public Bid() {
        this.timestamp = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public String getBiddingNumber() { return biddingNumber; }
    public void setBiddingNumber(String biddingNumber) { this.biddingNumber = biddingNumber; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Bid(Long userId, String biddingNumber, double amount, LocalDateTime timestamp, String status) {
        this.userId = userId;
        this.biddingNumber = biddingNumber;
        this.amount = amount;
        this.timestamp = timestamp;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Bid{" +
                "id=" + id +
                ", userId=" + userId +
                ", biddingNumber='" + biddingNumber + '\'' +
                ", amount=" + amount +
                ", timestamp=" + timestamp +
                ", status="+status +
                '}';
    }
}
