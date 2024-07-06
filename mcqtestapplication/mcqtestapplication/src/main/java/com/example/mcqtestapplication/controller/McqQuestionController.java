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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.mcqtestapplication.model.McqQuestionModel;
import com.example.mcqtestapplication.response.SuccessResponse;
import com.example.mcqtestapplication.service.McqQuestionService;

@RestController
@RequestMapping("/api/questions")
public class McqQuestionController {
    private static final Logger log = LoggerFactory.getLogger(McqQuestionController.class);
    @Autowired
    McqQuestionService questionService;

    @PostMapping()
    public ResponseEntity<SuccessResponse> createQuestion(@RequestBody McqQuestionModel question) {
        log.info("Request recieved for creating...");
        McqQuestionModel createdQuestion = questionService.creaQuestionModel(question);
        SuccessResponse response = new SuccessResponse("Question Store successfully", HttpStatus.CREATED.value(),
                createdQuestion);
        log.info("Send Response successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<SuccessResponse> getAllQuestions() {
        log.info("Request for fetch all questions");
        List<McqQuestionModel> questions = questionService.getAllQuestions();
        SuccessResponse response = new SuccessResponse("Data Fetch successfully", HttpStatus.OK.value(), questions);
        log.info("Fetch All questions");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse> getQuestionById(@PathVariable("id") int id) {
        log.info("Request for single question fetch");
        McqQuestionModel question = questionService.getQuestionById(id);
        SuccessResponse response = new SuccessResponse("Data fetch by using id", HttpStatus.FOUND.value(), question);
        log.info("Question fetch successfully");
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponse> updateQuestion(@PathVariable("id") int id,
            @RequestBody McqQuestionModel question) {
        log.info("Request for updating question");
        McqQuestionModel updatedQuestion = questionService.updateQuestion(id, question);
        SuccessResponse response = new SuccessResponse("Data Updated...", HttpStatus.OK.value(), updatedQuestion);
        log.info("Update Data successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable("id") int id) {
        log.info("Request for delete data");
        questionService.deleteQuestion(id);
        return ResponseEntity.ok("Data Deleted....");
    }

    @PostMapping("/bulkquestion")
    public ResponseEntity<SuccessResponse> saveBulkQuestion(@RequestParam("file") MultipartFile multipartFile) {
        List<McqQuestionModel> list = questionService.saveBulkQuestion(multipartFile);
        SuccessResponse successResponse = new SuccessResponse("Data Fetch successfully", HttpStatus.CREATED.value(),
                list);
        return new ResponseEntity<>(successResponse, HttpStatus.CREATED);
    }
}
