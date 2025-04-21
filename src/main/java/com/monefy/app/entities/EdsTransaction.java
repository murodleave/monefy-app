package com.monefy.app.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "transaction")
@EntityListeners(AuditingEntityListener.class)
public class EdsTransaction {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer objectID;

    @Column(nullable = false, precision = 18, scale = 2)
    private BigDecimal amount;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id")
    private EdsCategory category;

    @Column(name = "transaction_date")
    private LocalDateTime date = LocalDateTime.now();

    private String description;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private EdsUser user;

    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();

    public Integer getObjectID() {
        return objectID;
    }

    public void setObjectID(Integer objectID) {
        this.objectID = objectID;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public EdsCategory getCategory() {
        return category;
    }

    public void setCategory(EdsCategory category) {
        this.category = category;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EdsUser getUser() {
        return user;
    }

    public void setUser(EdsUser user) {
        this.user = user;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}
