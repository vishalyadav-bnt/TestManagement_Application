package com.example.mcqtestapplication.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mcq_question")
public class McqQuestionModel {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private int question_id;
    @NotBlank(message = "Question cannot be blank")
    private String question;
    
    @NotBlank(message = "Option One cannot be blank")
    private String optionOne;
    
    @NotBlank(message = "Option Two cannot be blank")
    private String optionTwo;
    
    @NotBlank(message = "Option Three cannot be blank")
    private String optionThree;
    
    @NotBlank(message = "Option Four cannot be blank")
    private String optionFour;
    
    @NotBlank(message = "Correct Option cannot be blank")
    private String correctOption;
    
    @NotNull(message = "Positive Mark is required")
    private int positiveMark;
    
    @NotNull(message = "Negative Mark is required")
    private int nagativeMark;
    @ManyToOne
	@JoinColumn(name="sub_category_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	SubCategoryModel subCategory;
}
    

