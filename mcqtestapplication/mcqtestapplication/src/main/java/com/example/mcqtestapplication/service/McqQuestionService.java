package com.example.mcqtestapplication.service;


import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.mcqtestapplication.model.McqQuestionModel;


public interface McqQuestionService {
    public McqQuestionModel creaQuestionModel(McqQuestionModel mcqQuestionModel);
    public List<McqQuestionModel> getAllQuestions();
    public McqQuestionModel getQuestionById(int id);
    public void deleteQuestion(int id);
    public McqQuestionModel updateQuestion(int id,McqQuestionModel mcqQuestionModel);
    public List<McqQuestionModel> saveBulkQuestion(MultipartFile multipartFile);

}
