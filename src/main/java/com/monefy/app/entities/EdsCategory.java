package com.monefy.app.entities;

import com.monefy.app.enums.CategoryType;
import com.monefy.app.items.CategoryItem;
import com.monefy.app.items.TransactionItem;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity(name = "category")
public class EdsCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer objectID;

    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private CategoryType type;

    @ManyToOne
    private EdsUser user;

    public Integer getObjectID() {
        return objectID;
    }

    public void setObjectID(Integer objectID) {
        this.objectID = objectID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CategoryType getType() {
        return type;
    }

    public void setType(CategoryType type) {
        this.type = type;
    }

    public EdsUser getUser() {
        return user;
    }

    public void setUser(EdsUser user) {
        this.user = user;
    }

    public CategoryItem toDto() {
        CategoryItem item = new CategoryItem();
        item.setCategoryID(getObjectID());
        item.setName(getName());
        item.setDescription(getDescription());
        item.setCategoryType(getType() != null ? getType().name() : null);
        item.setUserID(getUser() != null ? getUser().getObjectID() : null);
        return item;
    }
}
