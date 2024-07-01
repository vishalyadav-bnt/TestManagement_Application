package com.example.mcqtestapplication.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.mcqtestapplication.model.McqQuestionModel;
import com.example.mcqtestapplication.response.SuccessResponse;
import com.example.mcqtestapplication.service.McqQuestionServiceimpl;

@RestController
@RequestMapping("/api/questions")
public class McqQuestionController {
    private static final  Logger log=LoggerFactory.getLogger(McqQuestionController.class);
    @Autowired
    McqQuestionServiceimpl questionService;
    @PostMapping("/create")
    public ResponseEntity<SuccessResponse> createQuestion(@RequestBody McqQuestionModel question) {
        log.info("Request recieved for creating..."+question.getCategory());
        McqQuestionModel createdQuestion = questionService.creaQuestionModel(question);
        SuccessResponse response = new SuccessResponse("Question Store SuccesFully", HttpStatus.CREATED.value(),
                createdQuestion);
        log.info("Send Response Successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<SuccessResponse> getAllQuestions() {
        log.info("Request for fetch all questions");
        List<McqQuestionModel> questions = questionService.getAllQuestions();
        SuccessResponse response = new SuccessResponse("Data Fetch Succesfully", HttpStatus.OK.value(), questions);
        log.info("Fetch All questions");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse> getQuestionById(@PathVariable("id") int id) {
        log.info("Request for single question fetch");
        McqQuestionModel question = questionService.getQuestionById(id);
        SuccessResponse response = new SuccessResponse("Data fetch by using id", HttpStatus.FOUND.value(), question);
        log.info("Question fetch succesfully");
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponse> updateQuestion(@PathVariable("id") int id,
            @RequestBody McqQuestionModel question) {
        log.info("Request for updating question");
        McqQuestionModel updatedQuestion = questionService.updateQuestion(id, question);
        SuccessResponse response=new SuccessResponse("Data Updated...",HttpStatus.OK.value(),updatedQuestion);
        log.info("Update Data Succesfully");
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable("id") int id) {
        log.info("Request for delete data");
        questionService.deleteQuestion(id);
        return ResponseEntity.ok("Data Deleted....");
    }
}
