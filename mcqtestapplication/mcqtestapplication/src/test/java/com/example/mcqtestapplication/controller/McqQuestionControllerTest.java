package com.example.mcqtestapplication.controller;
import java.io.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import com.example.mcqtestapplication.model.McqQuestionModel;
import com.example.mcqtestapplication.response.SuccessResponse;
import com.example.mcqtestapplication.service.McqQuestionServiceimpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;

public class McqQuestionControllerTest {

    @Mock
    private McqQuestionServiceimpl questionService;

    @InjectMocks
    private McqQuestionController questionController;

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

        when(questionService.creaQuestionModel(any(McqQuestionModel.class))).thenReturn(mockQuestion);

        McqQuestionModel inputQuestion = new McqQuestionModel();
        inputQuestion.setQuestion("What is Spring Boot?");
        ResponseEntity<SuccessResponse> responseEntity = questionController.createQuestion(inputQuestion);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

    }

    @Test
    public void testGetAllQuestions() {
        List<McqQuestionModel> mockQuestions = new ArrayList<>();
        McqQuestionModel question1 = new McqQuestionModel();
        question1.setQuestion("What is Spring?");
        question1.setOptionOne("A framework");
        question1.setOptionTwo("A module");
        question1.setCorrectOption("A framework");
        question1.setPositiveMark(3);
        question1.setNagativeMark(-1);

        McqQuestionModel question2 = new McqQuestionModel();
        question2.setQuestion("What is Spring Boot?");
        question2.setOptionOne("A Java framework");
        question2.setOptionTwo("A Spring module");
        question2.setCorrectOption("A Spring project");
        question2.setPositiveMark(3);
        question2.setNagativeMark(-1);

        mockQuestions.add(question1);
        mockQuestions.add(question2);

        when(questionService.getAllQuestions()).thenReturn(mockQuestions);

        ResponseEntity<SuccessResponse> responseEntity = questionController.getAllQuestions();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testGetQuestionById() {
        McqQuestionModel mockQuestion = new McqQuestionModel();
        mockQuestion.setQuestion("What is Spring Boot?");
        mockQuestion.setOptionOne("A Java framework");
        mockQuestion.setOptionTwo("A Spring module");
        mockQuestion.setCorrectOption("A Spring project");
        mockQuestion.setPositiveMark(3);
        mockQuestion.setNagativeMark(-1);

        int questionId = 1;
        when(questionService.getQuestionById(questionId)).thenReturn(mockQuestion);

        ResponseEntity<SuccessResponse> responseEntity = questionController.getQuestionById(questionId);

        assertEquals(HttpStatus.FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void testUpdateQuestion() {
        McqQuestionModel mockQuestion = new McqQuestionModel();
        mockQuestion.setQuestion("What is Spring?");
        mockQuestion.setOptionOne("A framework");
        mockQuestion.setOptionTwo("A module");
        mockQuestion.setCorrectOption("A framework");
        mockQuestion.setPositiveMark(3);
        mockQuestion.setNagativeMark(-1);

        int questionId = 1;
        when(questionService.updateQuestion(questionId, mockQuestion)).thenReturn(mockQuestion);

        ResponseEntity<SuccessResponse> responseEntity = questionController.updateQuestion(questionId, mockQuestion);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

    }

    @Test
    public void testDeleteQuestion() {
        int questionId = 1;
        ResponseEntity<String> responseEntity = questionController.deleteQuestion(questionId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Data Deleted....", responseEntity.getBody());
    }


    @Test
    public void testSaveBulkQuestion() throws Exception {
        InputStream inputStream = new FileInputStream("C:\\Users\\vishal.yadav\\Downloads\\QuestionBank.xlsx");
        MockMultipartFile file = new MockMultipartFile("file", "QuestionBank.xlsx", "text/xlsx", inputStream);
        List<McqQuestionModel>list=new ArrayList<>();
        when(questionService.saveBulkQuestion(file)).thenReturn(list);
        ResponseEntity<SuccessResponse> newList=questionController.saveBulkQuestion(file);
        assertEquals(HttpStatus.CREATED, newList.getStatusCode());
    }
}
