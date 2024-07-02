package com.example.mcqtestapplication.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
        System.out.println("sddsds"+subCategoryModel.getSubCategoryDescription());
        log.info("Request coming in controller for save data"+subCategoryModel.getSubCategoryDescription());
        SubCategoryModel newSubCategoryModel=subCategoryServiceImpl.saveSubcategoryModel(subCategoryModel);
        SuccessResponse response=new SuccessResponse("Data Store Succesfully",HttpStatus.CREATED.value(),newSubCategoryModel);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }
    @GetMapping("/all")
    public ResponseEntity<SuccessResponse> getAllSubCategory() {
        log.info("Request for fetch all Subcategory");
        List<SubCategoryModel> subcategoryModels =subCategoryServiceImpl.getAllSubCategory();
        SuccessResponse response = new SuccessResponse("Data Fetch Succesfully", HttpStatus.OK.value(), subcategoryModels);
        log.info("Fetch All questions");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse> getSubCategoryById(@PathVariable("id") int id) {
        log.info("Request for single Subcategory fetch");
        SubCategoryModel subcategoryModel = subCategoryServiceImpl.getSubCategoryById(id);
        SuccessResponse response = new SuccessResponse("Data fetch by using id", HttpStatus.FOUND.value(), subcategoryModel);
        log.info("Question fetch succesfully");
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponse> updateSubCategory(@PathVariable("id") int id,
            @RequestBody SubCategoryModel subCategoryModel) {
        log.info("Request for updating Subcategory");
        SubCategoryModel updatedsubCategoryModel = subCategoryServiceImpl.updateSubCategory(id, subCategoryModel);
        SuccessResponse response=new SuccessResponse("Data Updated...",HttpStatus.OK.value(),updatedsubCategoryModel);
        log.info("Update Data Succesfully");
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSubCategory(@PathVariable("id") int id) {
        log.info("Request for delete data");
        subCategoryServiceImpl.deleteSubCategory(id);
        return ResponseEntity.ok("Data Deleted....");
    }
    

}
