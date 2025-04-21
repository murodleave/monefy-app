package com.monefy.app.services.impl;

import com.monefy.app.dtos.CategoryForm;
import com.monefy.app.entities.EdsCategory;
import com.monefy.app.entities.EdsUser;
import com.monefy.app.enums.CategoryType;
import com.monefy.app.repos.CategoryRepo;
import com.monefy.app.repos.UserRepo;
import com.monefy.app.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;
    private final UserRepo userRepo;

    @Autowired
    public CategoryServiceImpl(CategoryRepo categoryRepo, UserRepo userRepo) {
        this.categoryRepo = categoryRepo;
        this.userRepo = userRepo;
    }


}