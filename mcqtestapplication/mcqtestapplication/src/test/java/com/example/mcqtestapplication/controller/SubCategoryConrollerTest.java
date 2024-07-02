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
import com.example.mcqtestapplication.model.SubCategoryModel;
import com.example.mcqtestapplication.response.SuccessResponse;
import com.example.mcqtestapplication.service.SubCategoryServiceImpl;

public class SubCategoryConrollerTest {

    @Mock
    SubCategoryServiceImpl serviceImpl;

    @InjectMocks
    SubCategoryController subCategoryController;

      @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void saveSubCategorySuccess()
    {
    int subCategoryId=1;
    int categoryId=1;
     String categoryName="java";
     String categoryDescription="Collection";
     String subcategoryName="annotation";
     String subCategoryDescription="Annotation in spring";
     CategoryModel categoryModel=new CategoryModel(categoryId,categoryName,categoryDescription);
     SubCategoryModel subcategoryModel=new SubCategoryModel();
     subcategoryModel.setSubCategoryId(subCategoryId);

     subcategoryModel.setCategoryModel(categoryModel);
     subcategoryModel.setSubCategoryName(subcategoryName);
     subcategoryModel.setSubCategoryDescription(subCategoryDescription);
     when(serviceImpl.saveSubcategoryModel(subcategoryModel)).thenReturn(subcategoryModel);
     ResponseEntity<SuccessResponse> responseEntity=subCategoryController.saveSubcategoryModel(subcategoryModel);
     assertEquals(HttpStatus.CREATED,responseEntity.getStatusCode());
    }
    @Test
    public void getAllSubCategorySuccess()
    {
        int subCategoryId=1;
        int categoryId=1;
         String categoryName="java";
         String categoryDescription="Collection";
         String subcategoryName="annotation";
         String subCategoryDescription="Annotation in spring";
         CategoryModel categoryModel=new CategoryModel(categoryId,categoryName,categoryDescription);
         SubCategoryModel subcategoryModel=new SubCategoryModel();
         subcategoryModel.setSubCategoryId(subCategoryId);
         subcategoryModel.setCategoryModel(categoryModel);
         subcategoryModel.setSubCategoryName(subcategoryName);
         subcategoryModel.setSubCategoryDescription(subCategoryDescription);
     List<SubCategoryModel>list=new ArrayList<>();
     list.add(subcategoryModel);
     when(serviceImpl.getAllSubCategory()).thenReturn(list);
     ResponseEntity<SuccessResponse> responseEntity=subCategoryController.getAllSubCategory();
     assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    }

    @Test
    public void getSingleCategorySuccess()
    {
        int subCategoryId=1;
        int categoryId=1;
         String categoryName="java";
         String categoryDescription="Collection";
         String subcategoryName="annotation";
         String subCategoryDescription="Annotation in spring";
         CategoryModel categoryModel=new CategoryModel(categoryId,categoryName,categoryDescription);
         SubCategoryModel subcategoryModel=new SubCategoryModel();
         subcategoryModel.setSubCategoryId(subCategoryId);
    
         subcategoryModel.setCategoryModel(categoryModel);
         subcategoryModel.setSubCategoryName(subcategoryName);
         subcategoryModel.setSubCategoryDescription(subCategoryDescription);
     when(serviceImpl.getSubCategoryById(subCategoryId)).thenReturn(subcategoryModel);
     ResponseEntity<SuccessResponse> responseEntity=subCategoryController.getSubCategoryById(subCategoryId);
     assertEquals(HttpStatus.FOUND,responseEntity.getStatusCode());
    }

    @Test
    public void updateCategorySuccess()
    {
        int subCategoryId=1;
        int categoryId=1;
         String categoryName="java";
         String categoryDescription="Collection";
         String subcategoryName="annotation";
         String subCategoryDescription="Annotation in spring";
         CategoryModel categoryModel=new CategoryModel(categoryId,categoryName,categoryDescription);
         SubCategoryModel subcategoryModel=new SubCategoryModel();
         subcategoryModel.setSubCategoryId(subCategoryId);
         subcategoryModel.setCategoryModel(categoryModel);
         subcategoryModel.setSubCategoryName(subcategoryName);
         subcategoryModel.setSubCategoryDescription(subCategoryDescription);
     when(serviceImpl.updateSubCategory(subCategoryId,subcategoryModel)).thenReturn(subcategoryModel);
     ResponseEntity<SuccessResponse> responseEntity=subCategoryController.updateSubCategory(subCategoryId,subcategoryModel);
     assertEquals(HttpStatus.CREATED,responseEntity.getStatusCode());
    }


    @Test
    public void deleteCategorySuccess()
    {
     int subCategoryId=1;
     serviceImpl.deleteSubCategory(subCategoryId);
     ResponseEntity<String> responseEntity=subCategoryController.deleteSubCategory(subCategoryId);
     assertEquals("Data Deleted....",responseEntity.getBody());
    }

}
