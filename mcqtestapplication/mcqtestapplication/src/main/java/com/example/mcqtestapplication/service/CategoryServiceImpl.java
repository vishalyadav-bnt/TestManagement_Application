package com.example.mcqtestapplication.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.example.mcqtestapplication.exception.DataAllreadyPresent;
import com.example.mcqtestapplication.exception.DataIsNotPresent;
import com.example.mcqtestapplication.exception.IdNotFound;
import com.example.mcqtestapplication.exception.ObjectIsNull;
import com.example.mcqtestapplication.model.CategoryModel;
import com.example.mcqtestapplication.repositiory.CategoryRepositiory;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static final Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);
    @Autowired
    CategoryRepositiory categoryRepositiory;

    @Override
    public List<CategoryModel> getAllCategory() {
        log.info("coming in service for get all questions");
        List<CategoryModel> list = categoryRepositiory.findAll();
        if (list.isEmpty()) {
            log.info("throw excption");
            throw new DataIsNotPresent("Data is not present for fetching");
        }
        log.info("return succesfully");
        return list;
    }

    @Override
    public CategoryModel getCategoryById(int id) {
        log.info("comes in service for fetch single questions");
        Optional<CategoryModel> category = categoryRepositiory.findById(id);
        if (!category.isPresent()) {
            log.error("error occured");
            throw new IdNotFound("Id Not Found");
        }
        CategoryModel categorys = category.get();
        log.info("return from service with single questions model");
        return categorys;
    }

    @Override
    public void deleteCategory(int id) {
        log.info("comes in service for delete data");
        Optional<CategoryModel> categoryOptional = categoryRepositiory.findById(id);
        if (categoryOptional.isPresent()) {
            categoryRepositiory.deleteById(id);
            log.info("Delete data....");
        } else {
            log.error("throw Exception");
            throw new IdNotFound("Id Not Found");
        }
    }

    @Override
    public CategoryModel updateCategory(int id, CategoryModel categoryModel) {
        log.info("comimg in service for update data");
        Optional<CategoryModel> category = categoryRepositiory.findById(id);
        if (category.isPresent()) {
            CategoryModel categorys = category.get();
            categorys.setCategoryName(categoryModel.getCategoryName());
            categorys.setCategoryDescription(categoryModel.getCategoryDescription());
            CategoryModel updatedData = categoryRepositiory.save(categorys);
            log.info("return updated data");
            return updatedData;
        } else {
            log.error("Error occured");
            throw new IdNotFound("Id Is Not Present");
        }
    }

    @Override
    public CategoryModel saveCategory(CategoryModel categoryModel) {
        log.info("comes in service" + categoryModel.getCategoryDescription());
        if (categoryModel == null || categoryModel.getCategoryDescription() == null|| categoryModel.getCategoryName()==null||categoryModel.getCategoryName().isEmpty()) {
            log.error("Error Occured In Saving");
            throw new ObjectIsNull("Category model is empty");
        }
        Optional<CategoryModel> checkCategoryName=categoryRepositiory.findByCategoryName(categoryModel.getCategoryName());
        if(checkCategoryName.isPresent())
        {
           throw new DataAllreadyPresent("Category name is exist");
        }
        CategoryModel storeCategroyModel = categoryRepositiory.save(categoryModel);
        log.info("return from service");
        return storeCategroyModel;
    }

}
