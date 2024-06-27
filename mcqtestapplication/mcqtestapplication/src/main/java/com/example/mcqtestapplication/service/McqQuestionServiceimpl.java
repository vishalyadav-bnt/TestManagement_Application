package com.example.mcqtestapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mcqtestapplication.exception.QuestionModelIsEmpty;
import com.example.mcqtestapplication.model.McqQuestionModel;
import com.example.mcqtestapplication.repositiory.QuestionRepositiory;
@Service
public class McqQuestionServiceimpl implements McqQuestionService {
    @Autowired
    QuestionRepositiory questionRepositiory;

    @Override
    public McqQuestionModel creaQuestionModel(McqQuestionModel mcqQuestionModel) {
      if(mcqQuestionModel==null||mcqQuestionModel.getQuestion()==null)
      {
        throw new QuestionModelIsEmpty("Question model is empty");
      }
      McqQuestionModel stroeqQuestionModel=questionRepositiory.save(mcqQuestionModel);
      return stroeqQuestionModel;
    }

}
