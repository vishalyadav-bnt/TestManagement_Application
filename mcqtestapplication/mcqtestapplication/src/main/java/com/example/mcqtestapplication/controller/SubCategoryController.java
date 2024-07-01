package com.example.mcqtestapplication.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mcqtestapplication.model.SubCategoryModel;
import com.example.mcqtestapplication.response.SuccessResponse;
import com.example.mcqtestapplication.service.SubCategoryServiceImpl;

@RestController
@RequestMapping("api/subcategory")
public class SubCategoryController {
    @Autowired
    private SubCategoryServiceImpl subCategoryServiceImpl;
    private static final Logger log=LoggerFactory.getLogger(SubCategoryController.class);

    @PostMapping("/create")
    public ResponseEntity<SuccessResponse> saveSubcategoryModel(@RequestBody SubCategoryModel subCategoryModel)
    {
        log.info("Request coming in controller for save data");
        SubCategoryModel newSubCategoryModel=subCategoryServiceImpl.saveSubcategoryModel(subCategoryModel);
        SuccessResponse response=new SuccessResponse("Data Store Succesfully",HttpStatus.OK.value(),newSubCategoryModel);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }
    

}
