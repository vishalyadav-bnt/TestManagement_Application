package com.example.mcqtestapplication.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Subcategory")
public class SubCategoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer subCategoryId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private CategoryModel categoryModel;
    private String subCategoryName;
    private String subCategoryDescription;
}
