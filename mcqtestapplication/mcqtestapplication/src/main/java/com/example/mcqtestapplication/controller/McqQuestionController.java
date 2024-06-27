package com.example.mcqtestapplication.controller;

import java.util.Optional;

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
 @Autowired
 McqQuestionServiceimpl questionService;
 @PostMapping("/create")
    public ResponseEntity<SuccessResponse> createQuestion(@RequestBody McqQuestionModel question) {
        McqQuestionModel createdQuestion = questionService.creaQuestionModel(question);
        SuccessResponse response=new SuccessResponse("Question Store SuccesFully",HttpStatus.CREATED.value(),createdQuestion);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // @GetMapping("/all")
    // public ResponseEntity<SuccessResponse> getAllQuestions() {
    //     List<McqQuestionModel> questions = questionService.getAllQuestions();
    //     return new ResponseEntity<>(questions, HttpStatus.OK);
    // }

    // @GetMapping("/{id}")
    // public ResponseEntity<SuccessResponse> getQuestionById(@PathVariable("id") Long id) {
    //     // Optional<McqQuestionModel> question = questionService.getQuestionById(id);
    //     // return question.map(ResponseEntity::ok)
    //     //                .orElse(ResponseEntity.notFound().build());
    // }

    // @PutMapping("/{id}")
    // public ResponseEntity<SuccessResponse> updateQuestion(@PathVariable("id") Long id, @RequestBody McqQuestion question) {
    //     // McqQuestionModel updatedQuestion = questionService.updateQuestion(id, question);
    //     // return new ResponseEntity<>(updatedQuestion, HttpStatus.OK);
    // }

    // @DeleteMapping("/{id}")
    // public ResponseEntity<SuccessResponse> deleteQuestion(@PathVariable("id") Long id) {
    //     questionService.deleteQuestion(id);
    //     return ResponseEntity.noContent().build();
    // }
}
