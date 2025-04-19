package com.monefy.app.services;

import com.monefy.app.items.CategoryItem;

import java.util.List;

public interface CategoryService {

    CategoryItem create(CategoryItem dto);

    List<CategoryItem> getAllByUser(Integer userId);

    void delete(Integer id);

}
