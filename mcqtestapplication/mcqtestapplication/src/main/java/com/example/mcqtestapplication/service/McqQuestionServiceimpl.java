package com.example.mcqtestapplication.service;

import java.io.*;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.mcqtestapplication.exception.DataIsNotPresent;
import com.example.mcqtestapplication.exception.IdNotFound;
import com.example.mcqtestapplication.exception.QuestionModelIsEmpty;
import com.example.mcqtestapplication.model.McqQuestionModel;
import com.example.mcqtestapplication.model.SubCategoryModel;
import com.example.mcqtestapplication.repositiory.QuestionRepositiory;
import com.example.mcqtestapplication.repositiory.SubCategoryRepositiory;

@Service
public class McqQuestionServiceimpl implements McqQuestionService {
    private static final Logger log = LoggerFactory.getLogger(McqQuestionServiceimpl.class);

    @Autowired
    QuestionRepositiory questionRepositiory;

    @Autowired
    SubCategoryRepositiory subCategoryRepositiory;

    @Override
    public McqQuestionModel creaQuestionModel(McqQuestionModel mcqQuestionModel) {
        log.info("Request received in service for save question data");
        if (isMcqQuestionModelInvalid(mcqQuestionModel)) {
            System.out.println(isMcqQuestionModelInvalid(mcqQuestionModel));
            log.error("Error Occured......");
            throw new QuestionModelIsEmpty("Question model is empty");
        }
        McqQuestionModel stroeqQuestionModel = questionRepositiory.save(mcqQuestionModel);
        log.info("return from service");
        return stroeqQuestionModel;
    }

    @Override
    public List<McqQuestionModel> getAllQuestions() {
        log.info("Request received in service for get all questions");
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
        log.info("Request received in service for fetch single questions");
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
        log.info("Request received in service for delete data");
        Optional<McqQuestionModel> question = questionRepositiory.findById(id);
        if (question.isEmpty()) {
            log.error("throw Exception");
            throw new IdNotFound("Id Not Found");
        }
        questionRepositiory.deleteById(id);
        log.info("Delete data....");
    }

    @Override
    public McqQuestionModel updateQuestion(int id, McqQuestionModel mcqQuestionModel) {
        log.info("Request received in service for update data");
        Optional<McqQuestionModel> question = questionRepositiory.findById(id);
        if (question.isEmpty()) {
            log.error("Error occured");
            throw new IdNotFound("Id Is Not Present");
        }
        McqQuestionModel questions = question.get();
        questions.setSubCategory(mcqQuestionModel.getSubCategory());
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
    }

    @Override
    public List<McqQuestionModel> saveBulkQuestion(MultipartFile multipartFile) {
        log.info("Request received in service for upload bulk questionns");
        List<McqQuestionModel> questionBank = new ArrayList<>();
        try (InputStream inputStream = multipartFile.getInputStream(); // USe Try With Resource
                Workbook workbook = new XSSFWorkbook(inputStream)) {
            org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(0);
            for (int i = sheet.getFirstRowNum() + 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    McqQuestionModel multipleChoiceQuestion = new McqQuestionModel();
                    for (int j = (row.getFirstCellNum()); j < row.getLastCellNum(); j++) {
                        Cell cell = row.getCell(j);
                        switch (j) {
                            case 2:
                                String subCategoryName = cell.toString();
                                Optional<SubCategoryModel> subCategory = subCategoryRepositiory
                                        .findBySubCategoryName(subCategoryName);
                                if (!subCategory.isPresent()) {
                                    throw new DataIsNotPresent("Subcategory is not present");
                                }
                                SubCategoryModel setSubCategory = subCategory.get();
                                multipleChoiceQuestion.setSubCategory(setSubCategory);
                                break;
                            case 3:
                                multipleChoiceQuestion.setQuestion(cell.toString());
                                break;
                            case 4:
                                multipleChoiceQuestion.setOptionOne(cell.toString());
                                break;
                            case 5:
                                multipleChoiceQuestion.setOptionTwo(cell.toString());
                                break;
                            case 6:
                                multipleChoiceQuestion.setOptionThree(cell.toString());
                                break;
                            case 7:
                                multipleChoiceQuestion.setOptionFour(cell.toString());
                                break;
                            case 8:
                                multipleChoiceQuestion.setCorrectOption(cell.toString());
                                break;
                            case 9:
                                int positiveMark = (int) Double.parseDouble(cell.toString().trim());
                                multipleChoiceQuestion.setPositiveMark(positiveMark);
                                break;
                            case 10:
                                int nagativeMark = (int) Double.parseDouble(cell.toString().trim());
                                multipleChoiceQuestion.setNagativeMark(nagativeMark);
                                break;
                            default:
                                break;
                        }
                    }
                    McqQuestionModel savedQuestion = questionRepositiory.save(multipleChoiceQuestion);
                    questionBank.add(savedQuestion);
                } else {
                    log.info("Value of row is Null at index: " + i);
                    throw new DataIsNotPresent("SubCategory is null");
                }
            }
        } catch (IOException e) {
            log.error("Error reading file or closing resources", e);
        }
        return questionBank;
    }

    public static boolean isMcqQuestionModelInvalid(McqQuestionModel mcqQuestionModel) {
        return mcqQuestionModel.getQuestion() == null || mcqQuestionModel.getQuestion().isEmpty() ||
                mcqQuestionModel.getOptionOne() == null || mcqQuestionModel.getOptionOne().isEmpty() ||
                mcqQuestionModel.getOptionTwo() == null || mcqQuestionModel.getOptionTwo().isEmpty() ||
                mcqQuestionModel.getOptionThree() == null || mcqQuestionModel.getOptionThree().isEmpty() ||
                mcqQuestionModel.getOptionFour() == null || mcqQuestionModel.getOptionFour().isEmpty() ||
                mcqQuestionModel.getCorrectOption() == null || mcqQuestionModel.getCorrectOption().isEmpty() ||
                mcqQuestionModel.getPositiveMark() <= 0 ||
                mcqQuestionModel.getNagativeMark() <= 0 ||
                mcqQuestionModel.getSubCategory() == null;
    }

}
