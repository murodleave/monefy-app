package com.monefy.app.repos;

import com.monefy.app.entities.EdsUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<EdsUser, Integer> {

    Optional<EdsUser> findByUsername(String username);

    EdsUser getByUsername(String username);
}
