package com.monefy.app.repos;

import com.monefy.app.entities.EdsTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepo extends JpaRepository<EdsTransaction, Integer> {

    List<EdsTransaction> findByUser_ObjectID(Integer userID);
}

