package com.example.mcqtestapplication.repositiory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mcqtestapplication.model.McqQuestionModel;

import jakarta.persistence.Id;

public interface QuestionRepositiory extends JpaRepository<McqQuestionModel,Id>{

}
