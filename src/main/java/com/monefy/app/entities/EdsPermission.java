package com.monefy.app.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "permission")
public class EdsPermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer objectID;

    @Column(unique = true)
    private String code;

    private String name;

    private String description;

    public EdsPermission(String code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
    }

    public Integer getObjectID() {
        return objectID;
    }

    public void setObjectID(Integer objectID) {
        this.objectID = objectID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
}
