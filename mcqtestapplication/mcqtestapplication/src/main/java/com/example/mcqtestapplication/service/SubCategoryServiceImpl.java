package com.example.mcqtestapplication.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mcqtestapplication.exception.QuestionModelIsEmpty;
import com.example.mcqtestapplication.model.SubCategoryModel;
import com.example.mcqtestapplication.repositiory.SubCategoryRepositiory;
@Service
public class SubCategoryServiceImpl implements SubCategoryservice {
    private static final Logger log=LoggerFactory.getLogger(SubCategoryServiceImpl.class);
    @Autowired 
    SubCategoryRepositiory subCategoryRepositiory;

    @Override
    public SubCategoryModel saveSubcategoryModel(SubCategoryModel subCategoryModel) {
        log.info("Request coming in sarvices for save subcategoryModel");
      if(subCategoryModel==null)
      {
        log.error("Error Occured In Save");
        throw new QuestionModelIsEmpty("SubCategory Model IS Empty");
      }
      SubCategoryModel newSubCategoryModel=subCategoryRepositiory.save(subCategoryModel);
      log.info("Data Save And Return Response");
      return newSubCategoryModel;
    }

}
