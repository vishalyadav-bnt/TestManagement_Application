package com.example.mcqtestapplication.service;

import java.util.List;

import com.example.mcqtestapplication.model.CategoryModel;

public interface CategoryService {
    public CategoryModel saveCategory(CategoryModel categoryModel);
     public List<CategoryModel> getAllCategory();
    public CategoryModel getCategoryById(int id);
    public void deleteCategory(int id);
    public CategoryModel updateCategory(int id,CategoryModel categoryModel);
}
