package com.example.mcqtestapplication.controller;

import java.util.List;
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
        SuccessResponse response = new SuccessResponse("Question Store SuccesFully", HttpStatus.CREATED.value(),
                createdQuestion);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<SuccessResponse> getAllQuestions() {
        List<McqQuestionModel> questions = questionService.getAllQuestions();
        SuccessResponse response = new SuccessResponse("Data Fetch Succesfully", HttpStatus.OK.value(), questions);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse> getQuestionById(@PathVariable("id") int id) {
        McqQuestionModel question = questionService.getQuestionById(id);
        SuccessResponse response = new SuccessResponse("Data fetch by using id", HttpStatus.FOUND.value(), question);
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponse> updateQuestion(@PathVariable("id") int id,
            @RequestBody McqQuestionModel question) {
        McqQuestionModel updatedQuestion = questionService.updateQuestion(id, question);
        SuccessResponse response=new SuccessResponse("Data Updated...",HttpStatus.OK.value(),updatedQuestion);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable("id") int id) {
        questionService.deleteQuestion(id);
        return ResponseEntity.ok("Data Deleted....");
    }
}
