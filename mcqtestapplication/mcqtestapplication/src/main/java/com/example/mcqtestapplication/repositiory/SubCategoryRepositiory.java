package com.example.mcqtestapplication.repositiory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.mcqtestapplication.model.SubCategoryModel;

public interface SubCategoryRepositiory extends JpaRepository<SubCategoryModel, Integer> {
   Optional<SubCategoryModel> findBySubCategoryName(String subCategoryName);

}
