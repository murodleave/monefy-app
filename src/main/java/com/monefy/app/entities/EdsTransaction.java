package com.monefy.app.entities;


import com.monefy.app.items.TransactionItem;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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

    @ManyToOne(optional = false)
    @JoinColumn(name = "account_id")
    private EdsAccount account;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id")
    private EdsCategory category;

    @Column(nullable = false, precision = 18, scale = 2)
    private BigDecimal amount;

    @Column(name = "transaction_date")
    private LocalDateTime date;

    private String note;

    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();


    public Integer getObjectID() {
        return objectID;
    }

    public void setObjectID(Integer objectID) {
        this.objectID = objectID;
    }

    public EdsAccount getAccount() {
        return account;
    }

    public void setAccount(EdsAccount account) {
        this.account = account;
    }

    public EdsCategory getCategory() {
        return category;
    }

    public void setCategory(EdsCategory category) {
        this.category = category;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public TransactionItem toDto() {
        TransactionItem transactionItem = new TransactionItem();
        transactionItem.setTransactionId(getObjectID());
        transactionItem.setCategoryID(getCategory() != null ? getCategory().getObjectID() : null);
        transactionItem.setAccountID(getAccount() != null ? getAccount().getObjectID() : null);
        transactionItem.setAmount(getAmount());
        transactionItem.setDate(getDate());
        transactionItem.setNote(getNote());
        return transactionItem;
    }
}
