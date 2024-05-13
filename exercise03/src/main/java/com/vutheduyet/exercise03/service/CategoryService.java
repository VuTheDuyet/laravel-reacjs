package com.vutheduyet.exercise03.service;

import java.util.List;

import com.vutheduyet.exercise03.entity.Category;

public interface CategoryService {
    Category createCategory(Category category);
    Category getCategoryById(String categoryId);
    List<Category> getAllCategories();
    List<Category> getCategoriesByParentId(String parentId);
    List<Category> getAllCategoriesWithNullParentId();
    Category updateCategory(Category category);
    void deleteCategory(String categoryId);
}