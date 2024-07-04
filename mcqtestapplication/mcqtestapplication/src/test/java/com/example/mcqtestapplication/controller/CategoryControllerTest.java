package com.example.mcqtestapplication.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.mcqtestapplication.model.CategoryModel;
import com.example.mcqtestapplication.response.SuccessResponse;
import com.example.mcqtestapplication.service.CategoryServiceImpl;

public class CategoryControllerTest {
    @Mock
    CategoryServiceImpl categoryServiceImpl;
    @InjectMocks
    CategoryController categoryController;

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
        when(categoryServiceImpl.saveCategory(categoryModel)).thenReturn(categoryModel);
        ResponseEntity<SuccessResponse> responseEntity = categoryController.createCategory(categoryModel);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void getAllCategorySuccess() {
        int categoryId = 1;
        String categoryName = "java";
        String categoryDescription = "Collection";
        CategoryModel categoryModel = new CategoryModel(categoryId, categoryName, categoryDescription);
        List<CategoryModel> list = new ArrayList<>();
        list.add(categoryModel);
        when(categoryServiceImpl.getAllCategory()).thenReturn(list);
        ResponseEntity<SuccessResponse> responseEntity = categoryController.getAllCategory();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void getSingleCategorySuccess() {
        int categoryId = 1;
        String categoryName = "java";
        String categoryDescription = "Collection";
        CategoryModel categoryModel = new CategoryModel(categoryId, categoryName, categoryDescription);
        when(categoryServiceImpl.getCategoryById(categoryId)).thenReturn(categoryModel);
        ResponseEntity<SuccessResponse> responseEntity = categoryController.getCategoryById(categoryId);
        assertEquals(HttpStatus.FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void updateCategorySuccess() {
        int categoryId = 1;
        String categoryName = "java";
        String categoryDescription = "Collection";
        CategoryModel categoryModel = new CategoryModel(categoryId, categoryName, categoryDescription);
        when(categoryServiceImpl.updateCategory(categoryId, categoryModel)).thenReturn(categoryModel);
        ResponseEntity<SuccessResponse> responseEntity = categoryController.updateCategory(categoryId, categoryModel);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void deleteCategorySuccess() {
        int categoryId = 1;
        categoryServiceImpl.deleteCategory(categoryId);
        ResponseEntity<String> responseEntity = categoryController.deleteCategory(categoryId);
        assertEquals("Data Deleted....", responseEntity.getBody());
    }


   

}
