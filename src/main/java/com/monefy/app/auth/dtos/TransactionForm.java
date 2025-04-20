package com.monefy.app.auth.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionForm {

    private LocalDate date = LocalDate.now();

    private BigDecimal amount;

    private Long categoryId;

    private String description;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

