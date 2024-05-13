package com.vutheduyet.exercise03.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.vutheduyet.exercise03.entity.Category;
import com.vutheduyet.exercise03.repository.CategoryRepository;
import com.vutheduyet.exercise03.service.CategoryService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category getCategoryById(String categoryId) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        return optionalCategory.orElse(null);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category updateCategory(Category category) {
        Optional<Category> optionalExistingCategory = categoryRepository.findById(category.getCategoryId());
        if (optionalExistingCategory.isPresent()) {
            Category existingCategory = optionalExistingCategory.get();
            existingCategory.setParentId(category.getParentId());
            existingCategory.setCategoryName(category.getCategoryName());
            existingCategory.setCategoryDescription(category.getCategoryDescription());
            existingCategory.setIcon(category.getIcon());
            existingCategory.setImage(category.getImage());
            existingCategory.setPlaceholder(category.getPlaceholder());
            existingCategory.setActive(category.isActive());
            existingCategory.setUpdatedAt(LocalDateTime.now());
            existingCategory.setUpdatedBy(category.getUpdatedBy());
            Category updatedCategory = categoryRepository.save(existingCategory);
            return updatedCategory;
        }

        return null;
    }

    @Override
    public List<Category> getCategoriesByParentId(String parentId) {
        return categoryRepository.findAll().stream()
                .filter(category -> category.getParentId() != null
                        && category.getParentId().getCategoryId().equals(parentId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Category> getAllCategoriesWithNullParentId() {
        return categoryRepository.findAll().stream()
                .filter(category -> category.getParentId() == null)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCategory(String categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}
