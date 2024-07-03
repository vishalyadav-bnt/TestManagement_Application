package com.example.mcqtestapplication.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.mcqtestapplication.model.CategoryModel;
import com.example.mcqtestapplication.repositiory.CategoryRepositiory;

public class CategoryServiceTest {

    @Mock
    CategoryRepositiory categoryRepositiory;

    @InjectMocks
    CategoryServiceImpl categoryServiceImpl;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void saveCategorySuccess() {
        int categoryId = 1;
        String categoryName = "java";
        String categoryDescription = "Collection";
        CategoryModel categoryModel = new CategoryModel(categoryId, categoryName, categoryDescription);
        when(categoryRepositiory.save(categoryModel)).thenReturn(categoryModel);
        CategoryModel newCategoryModel = categoryServiceImpl.saveCategory(categoryModel);
        assertEquals(categoryModel, newCategoryModel);
    }

    @Test
    public void getAllCategorySuccess() {
        int categoryId = 1;
        String categoryName = "java";
        String categoryDescription = "Collection";
        List<CategoryModel> list = new ArrayList<>();
        CategoryModel categoryModel = new CategoryModel(categoryId, categoryName, categoryDescription);
        list.add(categoryModel);
        when(categoryRepositiory.findAll()).thenReturn(list);
        List<CategoryModel> newCategoryModel = categoryServiceImpl.getAllCategory();
        assertNotNull(newCategoryModel);
        assertFalse(newCategoryModel.isEmpty());
        assertEquals(1, newCategoryModel.size());
    }

    @Test
    public void deleteCategorySuccess() {
        int categoryId = 1;
        String categoryName = "java";
        String categoryDescription = "Collection";
        CategoryModel categoryModel = new CategoryModel(categoryId, categoryName, categoryDescription);
        when(categoryRepositiory.findById(categoryId)).thenReturn(Optional.of(categoryModel));
        when(categoryRepositiory.save(categoryModel)).thenReturn(categoryModel);
        categoryServiceImpl.deleteCategory(categoryId);
        assertEquals(categoryModel, categoryModel);
    }

    @Test
    public void updateCategorySuccess() {
        int categoryId = 1;
        String categoryName = "java";
        String categoryDescription = "Collection";
        CategoryModel categoryModel = new CategoryModel(categoryId, categoryName, categoryDescription);
        when(categoryRepositiory.findById(categoryId)).thenReturn(Optional.of(categoryModel));
        when(categoryRepositiory.save(categoryModel)).thenReturn(categoryModel);
        CategoryModel newCategoryModel = categoryServiceImpl.updateCategory(categoryId, categoryModel);
        assertEquals(categoryModel, newCategoryModel);
    }
}
