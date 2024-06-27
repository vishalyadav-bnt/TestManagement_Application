package com.example.mcqtestapplication.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mcqtestapplication.exception.DataIsNotPresent;
import com.example.mcqtestapplication.exception.IdNotFound;
import com.example.mcqtestapplication.exception.QuestionModelIsEmpty;
import com.example.mcqtestapplication.model.McqQuestionModel;
import com.example.mcqtestapplication.repositiory.QuestionRepositiory;

@Service
public class McqQuestionServiceimpl implements McqQuestionService {
    private static final Logger log=LoggerFactory.getLogger(McqQuestionServiceimpl.class);
    
    @Autowired
    QuestionRepositiory questionRepositiory;

    @Override
    public McqQuestionModel creaQuestionModel(McqQuestionModel mcqQuestionModel) {
        log.info("comes in service");
        if (mcqQuestionModel == null || mcqQuestionModel.getQuestion() == null) {
            log.error("Error Occured");
            throw new QuestionModelIsEmpty("Question model is empty");
        }
        McqQuestionModel stroeqQuestionModel = questionRepositiory.save(mcqQuestionModel);
        log.info("return from service");
        return stroeqQuestionModel;
    }

    @Override
    public List<McqQuestionModel> getAllQuestions() {
        log.info("coming in service for get all questions");
        List<McqQuestionModel> list = questionRepositiory.findAll();
        if (list.isEmpty()) {
            log.info("throw excption");
            throw new DataIsNotPresent("Data is not present for fetching");
        }
        log.info("return succesfully");
        return list;
    }

    @Override
    public McqQuestionModel getQuestionById(int id) {
        log.info("comes in service for fetch single questions");
        Optional<McqQuestionModel> question = questionRepositiory.findById(id);
        if (!question.isPresent()) {
            log.error("error occured");
            throw new IdNotFound("Id Not Found");
        }
        McqQuestionModel questions = question.get();
        log.info("return from service with single questions model");
        return questions;
    }

    @Override
    public void deleteQuestion(int id) {
        log.info("comes in service for delete data");
        Optional<McqQuestionModel> question = questionRepositiory.findById(id);
        if (question.isPresent()) {
            questionRepositiory.deleteById(id);
            log.info("Delete data....");
        } else {
            log.error("throw Exception");
            throw new IdNotFound("Id Not Found");
        }
    }

    @Override
    public McqQuestionModel updateQuestion(int id, McqQuestionModel mcqQuestionModel) {
        log.info("comimg in service for update data");
        Optional<McqQuestionModel> question = questionRepositiory.findById(id);
        if (question.isPresent()) {
            McqQuestionModel questions = question.get();
            questions.setCategory(mcqQuestionModel.getCategory());
            questions.setQuestion(mcqQuestionModel.getQuestion());
            questions.setOptionOne(mcqQuestionModel.getOptionOne());
            questions.setOptionTwo(mcqQuestionModel.getOptionTwo());
            questions.setOptionThree(mcqQuestionModel.getOptionThree());
            questions.setOptionFour(mcqQuestionModel.getOptionFour());
            questions.setCorrectOption(mcqQuestionModel.getCorrectOption());
            questions.setPositiveMark(mcqQuestionModel.getPositiveMark());
            questions.setNagativeMark(mcqQuestionModel.getNagativeMark());
            McqQuestionModel updatedData = questionRepositiory.save(questions);
            log.info("return updated data");
            return updatedData;
        } else {
            log.error("Error occured");
            throw new IdNotFound("Id Is Not Present");
        }
    }
}
