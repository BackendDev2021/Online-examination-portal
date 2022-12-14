package com.training.portal.controller;

import com.training.portal.model.Question;
import com.training.portal.service.QuestionService;
import com.training.portal.utils.Constants;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/online-training-portal-application")
@Validated
@Slf4j
public class QuestionController {

	@Autowired
	QuestionService questionService;

	/**
	 * Add questions to exam portal by trainer(admin)
	 *[ Secured by Spring security]
	 *
	 * @param question
	 * @return
	 */
	@Secured(value = Constants.ROLE_AD)
	@PostMapping("/question")
	public ResponseEntity<Question> addQuestion(@RequestBody Question question){
		log.warn("Authenticated access to add question");
		return ResponseEntity.status(HttpStatus.CREATED).body(questionService.addQuestion(question));
	}

	/**
	 * Get all questions from repo with answers
	 * [Secured by Spring security]
	 * @param pageNo
	 * @param offset
	 * @return
	 */
	@Secured(value = Constants.ROLE_AD)
	@GetMapping("/questions")
	public ResponseEntity<Page<Question>> allQuestionsWithAnswer(@RequestParam Integer pageNo, Integer offset){
		log.info("All questions from repo by user requesting page - {},size-{}",pageNo,offset);
		return ResponseEntity.status(HttpStatus.OK).body(questionService.allQuestionsWithAnswer(pageNo,offset));
	}
}
