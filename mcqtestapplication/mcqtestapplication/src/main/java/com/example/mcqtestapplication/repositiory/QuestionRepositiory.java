package com.example.mcqtestapplication.repositiory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mcqtestapplication.model.McqQuestionModel;



public interface QuestionRepositiory extends JpaRepository<McqQuestionModel,Integer>{

}
