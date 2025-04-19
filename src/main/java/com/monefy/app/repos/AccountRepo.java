package com.monefy.app.repos;

import com.monefy.app.entities.EdsAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountRepo extends JpaRepository<EdsAccount, Integer> {


}