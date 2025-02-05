package com.example.mcqtestapplication.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mcqtestapplication.exception.DataAllreadyPresent;
import com.example.mcqtestapplication.exception.DataIsNotPresent;
import com.example.mcqtestapplication.exception.IdNotFound;
import com.example.mcqtestapplication.exception.ObjectIsNull;
import com.example.mcqtestapplication.model.SubCategoryModel;
import com.example.mcqtestapplication.repositiory.SubCategoryRepositiory;

@Service
public class SubCategoryServiceImpl implements SubCategoryservice {
  private static final Logger log = LoggerFactory.getLogger(SubCategoryServiceImpl.class);
  @Autowired
  SubCategoryRepositiory subCategoryRepositiory;

  @Override
  public SubCategoryModel saveSubcategoryModel(SubCategoryModel subCategoryModel) {
    log.info("Request received in service for save subcategoryModel");
    if (isSubCategoryModelInvalid(subCategoryModel)) {
      log.error("Error Occured In Save");
      throw new ObjectIsNull("SubCategory Model IS Empty");
    }
    Optional<SubCategoryModel> checkCategoryName = subCategoryRepositiory
        .findBySubCategoryName(subCategoryModel.getSubCategoryName());
    if (checkCategoryName.isPresent()) {
      throw new DataAllreadyPresent("SubCategory name is allready present");
    }
    SubCategoryModel newSubCategoryModel = subCategoryRepositiory.save(subCategoryModel);
    log.info("Data Save And Return Response");
    return newSubCategoryModel;
  }

  @Override
  public List<SubCategoryModel> getAllSubCategory() {
    log.info("Request received in service for get all subcategory");
    List<SubCategoryModel> list = subCategoryRepositiory.findAll();
    if (list.isEmpty()) {
      log.info("Error occured in getAll");
      throw new DataIsNotPresent("Data is not present for fetching");
    }
    log.info("return succesfully");
    return list;
  }

  @Override
  public SubCategoryModel getSubCategoryById(int id) {
    log.info(" Request received in service for fetch single subcategory");
    Optional<SubCategoryModel> subcategory = subCategoryRepositiory.findById(id);
    if (!subcategory.isPresent()) {
      log.error("error occured in getall");
      throw new IdNotFound("Id Not Found");
    }
    SubCategoryModel subcategorys = subcategory.get();
    log.info("return from service with single category model");
    return subcategorys;
  }

  @Override
  public void deleteSubCategory(int id) {
    log.info("Request received in service for delete data");
    Optional<SubCategoryModel> categoryOptional = subCategoryRepositiory.findById(id);
    if (!categoryOptional.isPresent()) {
      log.error("Error occured in delete ");
      throw new IdNotFound("Id Not Found");
    }
    subCategoryRepositiory.deleteById(id);
    log.info("Delete data....");
  }

  @Override
  public SubCategoryModel updateSubCategory(int id, SubCategoryModel subCategoryModel) {
    log.info("Request received in service for update data");
    Optional<SubCategoryModel> subcategory = subCategoryRepositiory.findById(id);
    if (!subcategory.isPresent()) {
      log.error("Error occured in update");
      throw new IdNotFound("Id Is Not Present");
    }
    SubCategoryModel updatesubModel = subcategory.get();
    updatesubModel.setSubCategoryName(subCategoryModel.getSubCategoryName());
    updatesubModel.setSubCategoryDescription(subCategoryModel.getSubCategoryDescription());
    SubCategoryModel updatedData = subCategoryRepositiory.save(updatesubModel);
    log.info("return updated data");
    return updatedData;
  }


  public static boolean isSubCategoryModelInvalid(SubCategoryModel subCategoryModel) {
    return subCategoryModel == null ||
           subCategoryModel.getCategoryModel() == null ||
           subCategoryModel.getSubCategoryName() == null ||
           subCategoryModel.getSubCategoryName().isEmpty();
}

}
