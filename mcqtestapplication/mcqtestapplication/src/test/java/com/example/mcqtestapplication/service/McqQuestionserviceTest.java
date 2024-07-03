package com.example.mcqtestapplication.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.mcqtestapplication.model.McqQuestionModel;
import com.example.mcqtestapplication.repositiory.QuestionRepositiory;

public class McqQuestionserviceTest {
    @Mock
    QuestionRepositiory questionRepositiory;

    @InjectMocks
    McqQuestionServiceimpl mcqQuestionServiceimpl;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateQuestion() {
        McqQuestionModel mockQuestion = new McqQuestionModel();
        mockQuestion.setQuestion("What is Spring Boot?");
        mockQuestion.setOptionOne("A Java framework");
        mockQuestion.setOptionTwo("A Spring module");
        mockQuestion.setOptionThree("A Spring project");
        mockQuestion.setOptionFour("An annotation");
        mockQuestion.setCorrectOption("A Spring project");
        mockQuestion.setPositiveMark(3);
        mockQuestion.setNagativeMark(-1);
        when(questionRepositiory.save(mockQuestion)).thenReturn(mockQuestion);
        McqQuestionModel mcqQuestionModel = mcqQuestionServiceimpl.creaQuestionModel(mockQuestion);
        assertEquals(mockQuestion, mcqQuestionModel);
    }

    @Test
    public void readQuestion() {
        McqQuestionModel mockQuestion = new McqQuestionModel();
        mockQuestion.setQuestion("What is Spring Boot?");
        mockQuestion.setOptionOne("A Java framework");
        mockQuestion.setOptionTwo("A Spring module");
        mockQuestion.setOptionThree("A Spring project");
        mockQuestion.setOptionFour("An annotation");
        mockQuestion.setCorrectOption("A Spring project");
        mockQuestion.setPositiveMark(3);
        mockQuestion.setNagativeMark(-1);
        List<McqQuestionModel> list = new ArrayList<>();
        list.add(mockQuestion);
        when(questionRepositiory.findAll()).thenReturn(list);

        List<McqQuestionModel> allquestion = mcqQuestionServiceimpl.getAllQuestions();

        assertNotNull(allquestion);
        assertFalse(allquestion.isEmpty());
        assertEquals(1, allquestion.size());

    }

    @Test
    public void deleteQuestion() {
        int id = 2;
        McqQuestionModel mockQuestion = new McqQuestionModel();
        mockQuestion.setQuestion("What is Spring Boot?");
        mockQuestion.setOptionOne("A Java framework");
        mockQuestion.setOptionTwo("A Spring module");
        mockQuestion.setOptionThree("A Spring project");
        mockQuestion.setOptionFour("An annotation");
        mockQuestion.setCorrectOption("A Spring project");
        mockQuestion.setPositiveMark(3);
        mockQuestion.setNagativeMark(-1);
        when(questionRepositiory.findById(id)).thenReturn(Optional.of(mockQuestion));

        assertEquals(mockQuestion, mockQuestion);
    }

    @Test
    public void testUpdate() {
        int id = 2;
        McqQuestionModel mockQuestion = new McqQuestionModel();
        mockQuestion.setQuestion("What is Spring Boot?");
        mockQuestion.setOptionOne("A Java framework");
        mockQuestion.setOptionTwo("A Spring module");
        mockQuestion.setOptionThree("A Spring project");
        mockQuestion.setOptionFour("An annotation");
        mockQuestion.setCorrectOption("A Spring project");
        mockQuestion.setPositiveMark(3);
        mockQuestion.setNagativeMark(-1);
        when(questionRepositiory.findById(id)).thenReturn(Optional.of(mockQuestion));
        when(questionRepositiory.save(any(McqQuestionModel.class))).thenReturn(mockQuestion);
        McqQuestionModel mcqQuestionModel = mcqQuestionServiceimpl.updateQuestion(id, mockQuestion);
        assertEquals(mockQuestion, mcqQuestionModel);
    }

}
