package com.monefy.app.services.impl;


import com.monefy.app.entities.EdsUser;
import com.monefy.app.repos.UserRepo;
import com.monefy.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo repo;

    @Autowired
    public UserServiceImpl(UserRepo repo) {
        this.repo = repo;
    }

    @Override
    public EdsUser getUser(String username) {
        return repo.getByUsername(username);
    }
}
