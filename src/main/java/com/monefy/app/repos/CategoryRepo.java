package com.monefy.app.repos;

import com.monefy.app.entities.EdsCategory;
import com.monefy.app.entities.EdsTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepo extends JpaRepository<EdsCategory, Integer> {

    List<EdsCategory> findByUser_ObjectID(Integer userID);

}