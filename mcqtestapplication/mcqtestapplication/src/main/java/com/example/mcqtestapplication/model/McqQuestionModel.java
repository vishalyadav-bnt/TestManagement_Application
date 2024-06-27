package com.example.mcqtestapplication.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private String category;
    private String question;
    private String optionOne;
    private String optionTwo;
    private String optionThree;
    private String optionFour;
    private String correctOption;
    private int positiveMark;
    private int nagativeMark;
}
