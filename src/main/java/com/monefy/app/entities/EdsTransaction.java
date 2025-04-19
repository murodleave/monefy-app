package com.monefy.app.entities;


import com.monefy.app.items.TransactionItem;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity(name = "transaction")
public class EdsTransaction {

    @Id
    @GeneratedValue
    private Integer objectID;

    private String amount;

    private String date;

    private String description;

    @ManyToOne
    private EdsUser user;

    @ManyToOne
    private EdsCategory category;

    public Integer getObjectID() {
        return objectID;
    }

    public void setObjectID(Integer objectID) {
        this.objectID = objectID;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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

    public EdsCategory getCategory() {
        return category;
    }

    public void setCategory(EdsCategory category) {
        this.category = category;
    }

    public TransactionItem toDto() {
        TransactionItem item = new TransactionItem();
        item.setTransactionId(getObjectID());
        item.setCategoryID(getCategory() != null ? getCategory().getObjectID() : null);
        item.setUserID(getUser() != null ? getUser().getObjectID() : null);
        item.setAmount(getAmount());
        item.setDescription(getDescription());
        item.setDate(getDate());
        return item;
    }
}
