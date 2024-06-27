package com.example.mcqtestapplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mcqtestapplication.exception.DataIsNotPresent;
import com.example.mcqtestapplication.exception.IdNotFound;
import com.example.mcqtestapplication.exception.QuestionModelIsEmpty;
import com.example.mcqtestapplication.model.McqQuestionModel;
import com.example.mcqtestapplication.repositiory.QuestionRepositiory;

@Service
public class McqQuestionServiceimpl implements McqQuestionService {
    @Autowired
    QuestionRepositiory questionRepositiory;

    @Override
    public McqQuestionModel creaQuestionModel(McqQuestionModel mcqQuestionModel) {
        if (mcqQuestionModel == null || mcqQuestionModel.getQuestion() == null) {
            throw new QuestionModelIsEmpty("Question model is empty");
        }
        McqQuestionModel stroeqQuestionModel = questionRepositiory.save(mcqQuestionModel);
        return stroeqQuestionModel;
    }

    @Override
    public List<McqQuestionModel> getAllQuestions() {
        List<McqQuestionModel> list = questionRepositiory.findAll();
        if (list.isEmpty()) {
            throw new DataIsNotPresent("Data is not present for fetching");
        }
        return list;
    }

    @Override
    public McqQuestionModel getQuestionById(int id) {
        Optional<McqQuestionModel> question = questionRepositiory.findById(id);
        if (!question.isPresent()) {
            throw new IdNotFound("Id Not Found");
        }
        McqQuestionModel questions = question.get();
        return questions;
    }

    @Override
    public void deleteQuestion(int id) {
        Optional<McqQuestionModel> question = questionRepositiory.findById(id);
        if (question.isPresent()) {
            questionRepositiory.deleteById(id);
        } else {
            throw new IdNotFound("Id Not Found");
        }
    }

    @Override
    public McqQuestionModel updateQuestion(int id, McqQuestionModel mcqQuestionModel) {
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
            return updatedData;
        } else {
            throw new IdNotFound("Id Is Not Present");
        }
    }
}
