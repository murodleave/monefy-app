package com.monefy.app.services.impl;

import com.monefy.app.entities.EdsCategory;
import com.monefy.app.entities.EdsUser;
import com.monefy.app.enums.CategoryType;
import com.monefy.app.items.CategoryItem;
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

    @Override
    public CategoryItem create(CategoryItem dto) {
        EdsUser user = userRepo.findById(dto.getUserID()).orElseThrow();

        EdsCategory category = new EdsCategory();
        category.setName(dto.getName());
        category.setType(CategoryType.valueOf(dto.getCategoryType()));
        category.setUser(user);

        return categoryRepo.save(category).toDto();
    }

    @Override
    public List<CategoryItem> getAllByUser(Integer userID) {
        List<CategoryItem> categoryItems = new ArrayList<>();
        List<EdsCategory> byUserObjectID = categoryRepo.findByUser_ObjectID(userID);
        for (EdsCategory edsCategory : byUserObjectID) {
            categoryItems.add(edsCategory.toDto());
        }
        return categoryItems;
    }

    @Override
    public void delete(Integer id) {
        categoryRepo.deleteById(id);
    }
}