package com.example.mcqtestapplication.repositiory;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mcqtestapplication.model.CategoryModel;

public interface CategoryRepositiory extends JpaRepository<CategoryModel,Integer>{

    Optional<CategoryModel> findByCategoryName(String categoryName);

}
