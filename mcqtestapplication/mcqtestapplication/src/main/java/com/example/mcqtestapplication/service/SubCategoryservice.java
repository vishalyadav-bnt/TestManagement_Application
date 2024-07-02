package com.example.mcqtestapplication.service;

import java.util.List;
import com.example.mcqtestapplication.model.SubCategoryModel;

public interface SubCategoryservice {
    public SubCategoryModel saveSubcategoryModel(SubCategoryModel subCategoryModel);
    public List<SubCategoryModel> getAllSubCategory();
    public SubCategoryModel getSubCategoryById(int id);
    public void deleteSubCategory(int id);
    public SubCategoryModel updateSubCategory(int id,SubCategoryModel subCategoryModel);
}
