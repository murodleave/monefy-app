package com.monefy.app.repos;

import com.monefy.app.entities.EdsTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepo extends JpaRepository<EdsTransaction, Integer> {

    List<EdsTransaction> findByAccount_User_ObjectID(Integer userId);

}

