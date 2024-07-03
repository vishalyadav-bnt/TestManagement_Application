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

import com.example.mcqtestapplication.model.CategoryModel;
import com.example.mcqtestapplication.response.SuccessResponse;
import com.example.mcqtestapplication.service.CategoryServiceImpl;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryServiceImpl categoryServiceImpl;
    private static final Logger log = LoggerFactory.getLogger(CategoryController.class);

    @PostMapping("/create")
    public ResponseEntity<SuccessResponse> createCategory(@RequestBody CategoryModel categoryModel) {
        log.info("Request recieved for creating Category...");
        CategoryModel createdCategory = categoryServiceImpl.saveCategory(categoryModel);
        SuccessResponse response = new SuccessResponse("Category Store SuccesFully", HttpStatus.CREATED.value(),
                createdCategory);
        log.info("Send Response Successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<SuccessResponse> getAllCategory() {
        log.info("Request for fetch all category");
        List<CategoryModel> categoryModels = categoryServiceImpl.getAllCategory();
        SuccessResponse response = new SuccessResponse("Data Fetch Succesfully", HttpStatus.OK.value(), categoryModels);
        log.info("Fetch All questions");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse> getCategoryById(@PathVariable("id") int id) {
        log.info("Request for single category fetch");
        CategoryModel categoryModel = categoryServiceImpl.getCategoryById(id);
        SuccessResponse response = new SuccessResponse("Data fetch by using id", HttpStatus.FOUND.value(),
                categoryModel);
        log.info("Question fetch succesfully");
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponse> updateCategory(@PathVariable("id") int id,
            @RequestBody CategoryModel categoryModel) {
        log.info("Request for updating category");
        CategoryModel updatedCategoryModel = categoryServiceImpl.updateCategory(id, categoryModel);
        SuccessResponse response = new SuccessResponse("Data Updated...", HttpStatus.CREATED.value(),
                updatedCategoryModel);
        log.info("Update Data Succesfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") int id) {
        log.info("Request for delete data");
        categoryServiceImpl.deleteCategory(id);
        return ResponseEntity.ok("Data Deleted....");
    }
     
   

}
