package com.training.portal.controller;

import com.training.portal.model.AnswerRequest;
import com.training.portal.model.QuestionResponse;
import com.training.portal.model.SetQuestionVo;
import com.training.portal.service.PortalService;
import com.training.portal.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
public class MainPortalController {

	@Autowired
	PortalService portalService;

	/**
	 * Create the question paper to trainees by the trainer (admin)
	 * [Secured  by spring security]
	 *
	 * @param question
	 * @return
	 */
	@Secured(value = Constants.ROLE_AD)
	@PostMapping("/examination/set-question-paper")
	public ResponseEntity<SetQuestionVo> setQuestionDetails(@RequestBody SetQuestionVo question) {
		log.warn("Authenticated access occurred");
		return ResponseEntity.status(HttpStatus.CREATED).body(portalService.setQuestionDetails(question));
	}

	/**
	 * Get all the questions which is asked for examination
	 * [Secured by Spring security]
	 * @return
	 */
	@Secured(value = Constants.ROLE_AD)
	@GetMapping("/examination/all-questions")
	public ResponseEntity<Collection<QuestionResponse>> loadQuestions() {
		log.info("Questions from repo");
		return ResponseEntity.status(HttpStatus.OK).body(portalService.loadQuestions());
	}

	/**
	 * Api which is start the examination and by trainees
	 *
	 * @param request
	 * @return
	 */
	@PostMapping("/examination/start-test")
	public ResponseEntity<String> questions(@RequestBody AnswerRequest request){
		log.warn("Examination process started");
		return ResponseEntity.status(HttpStatus.OK).body(portalService.questions(request));
	}
}
