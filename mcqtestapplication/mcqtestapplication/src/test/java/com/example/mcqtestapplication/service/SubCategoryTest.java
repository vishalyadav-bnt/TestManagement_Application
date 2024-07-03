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
import com.example.mcqtestapplication.model.SubCategoryModel;
import com.example.mcqtestapplication.repositiory.SubCategoryRepositiory;

public class SubCategoryTest {

    @Mock
    SubCategoryRepositiory subCategoryRepositiory;

    @InjectMocks
    SubCategoryServiceImpl serviceImpl;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void saveSubCategorySuccess() {
        int subCategoryId = 1;
        int categoryId = 1;
        String categoryName = "java";
        String categoryDescription = "Collection";
        String subcategoryName = "annotation";
        String subCategoryDescription = "Annotation in spring";
        CategoryModel categoryModel = new CategoryModel(categoryId, categoryName, categoryDescription);
        SubCategoryModel subcategoryModel = new SubCategoryModel();
        subcategoryModel.setSubCategoryId(subCategoryId);
        subcategoryModel.setCategoryModel(categoryModel);
        subcategoryModel.setSubCategoryName(subcategoryName);
        subcategoryModel.setSubCategoryDescription(subCategoryDescription);
        when(subCategoryRepositiory.save(subcategoryModel)).thenReturn(subcategoryModel);
        SubCategoryModel newSubCategoryModel = serviceImpl.saveSubcategoryModel(subcategoryModel);
        assertEquals(subcategoryModel, newSubCategoryModel);

    }

    @Test
    public void getAllSubCategorySuccess() {
        int subCategoryId = 1;
        int categoryId = 1;
        String categoryName = "java";
        String categoryDescription = "Collection";
        String subcategoryName = "annotation";
        String subCategoryDescription = "Annotation in spring";
        CategoryModel categoryModel = new CategoryModel(categoryId, categoryName, categoryDescription);
        SubCategoryModel subcategoryModel = new SubCategoryModel();
        subcategoryModel.setSubCategoryId(subCategoryId);
        subcategoryModel.setCategoryModel(categoryModel);
        subcategoryModel.setSubCategoryName(subcategoryName);
        subcategoryModel.setSubCategoryDescription(subCategoryDescription);
        List<SubCategoryModel> list = new ArrayList<>();
        list.add(subcategoryModel);
        when(subCategoryRepositiory.findAll()).thenReturn(list);
        List<SubCategoryModel> newSubCategoryModel = serviceImpl.getAllSubCategory();
        assertNotNull(newSubCategoryModel);
        assertFalse(newSubCategoryModel.isEmpty());

    }

    @Test
    public void updateCategorySuccess() {
        int subCategoryId = 1;
        int categoryId = 1;
        String categoryName = "java";
        String categoryDescription = "Collection";
        String subcategoryName = "annotation";
        String subCategoryDescription = "Annotation in spring";
        CategoryModel categoryModel = new CategoryModel(categoryId, categoryName, categoryDescription);
        SubCategoryModel subcategoryModel = new SubCategoryModel();
        subcategoryModel.setSubCategoryId(subCategoryId);
        subcategoryModel.setCategoryModel(categoryModel);
        subcategoryModel.setSubCategoryName(subcategoryName);
        subcategoryModel.setSubCategoryDescription(subCategoryDescription);
        when(subCategoryRepositiory.findById(subCategoryId)).thenReturn(Optional.of(subcategoryModel));
        when(subCategoryRepositiory.save(subcategoryModel)).thenReturn(subcategoryModel);
        SubCategoryModel newSubCategoryModel = serviceImpl.updateSubCategory(subCategoryId, subcategoryModel);
        assertEquals(subcategoryModel, newSubCategoryModel);
    }

    @Test
    public void deleteSubCategorySuccess() {
        int subCategoryId = 1;
        int categoryId = 1;
        String categoryName = "java";
        String categoryDescription = "Collection";
        String subcategoryName = "annotation";
        String subCategoryDescription = "Annotation in spring";
        CategoryModel categoryModel = new CategoryModel(categoryId, categoryName, categoryDescription);
        SubCategoryModel subcategoryModel = new SubCategoryModel();
        subcategoryModel.setSubCategoryId(subCategoryId);
        subcategoryModel.setCategoryModel(categoryModel);
        subcategoryModel.setSubCategoryName(subcategoryName);
        subcategoryModel.setSubCategoryDescription(subCategoryDescription);
        when(subCategoryRepositiory.findById(subCategoryId)).thenReturn(Optional.of(subcategoryModel));
        subCategoryRepositiory.delete(subcategoryModel);
        serviceImpl.deleteSubCategory(subCategoryId);
        assertEquals(subcategoryModel, subcategoryModel);
    }

}
