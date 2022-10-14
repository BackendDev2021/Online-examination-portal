package com.training.portal.service;

import com.training.portal.exception.BusinessException;
import com.training.portal.exception.ErrorDetails;
import com.training.portal.model.*;
import com.training.portal.repository.AnswerRepo;
import com.training.portal.repository.QuestionRepo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PortalService {

	@Autowired
	private QuestionRepo questionRepo;

	@Autowired
	private AnswerRepo answerRepo;

	public SetQuestionVo setQuestionDetails(SetQuestionVo question) {
		Trainer setQuestionTrainer = new Trainer();
		SetQuestionVo response = new SetQuestionVo();
		Question setQuestion = new Question();
		Question findQuestion = questionRepo.findById(question.getQuestionId()).get();
		setQuestionTrainer.setName(question.getTrainerName());
		setQuestionTrainer.setLocation(question.getLocation());
		setQuestionTrainer.setTechnology(question.getTechnology());
		setQuestion.setDescription(findQuestion.getDescription());
		setQuestion.setSubject(question.getSubject());
		setQuestion.setQuestionType(findQuestion.getQuestionType());
		setQuestion.setSubjectId(findQuestion.getSubjectId());
		setQuestion.setDate(LocalDate.now());
		setQuestion.setIsAnswered(false);
		setQuestion.setAnswerKey(question.getAnswerKey());
		Answer setAnswers = new Answer();
		Collection<AnswerVo> savedOptions = new ArrayList<>();
		AnswerVo answerVo = new AnswerVo();
//		question.getAnswerVo().stream().forEach(answer ->{
//			answerVo.setAnswerId(setQuestion.getAnswerKey());
//			answerVo.setQuestionId(findQuestion.getId());
//			answerVo.setAnswer(answer.getAnswer());
//			answerVo.setIsCorrectAnswer(answer.getIsCorrectAnswer());
//		});
		savedOptions.addAll(question.getAnswerVo());
		setAnswers.setQuestionId(setQuestion.getId());
		setAnswers.setAnswer(answerVo.getAnswer());
		setAnswers.setIscorrectAnswer(answerVo.getIsCorrectAnswer());
		questionRepo.save(setQuestion);
		Collection<AnswerVo>  options = answerRepo.saveAll(savedOptions);
		response.setQuestionId(findQuestion.getId());
		response.setTrainerName(setQuestionTrainer.getName());
		response.setLocation(setQuestionTrainer.getLocation());
		response.setTechnology(setQuestionTrainer.getTechnology());
		response.setSubject(setQuestion.getSubject());
		response.setQuestionDescription(setQuestion.getDescription());
		response.setAnswerKey(setQuestion.getAnswerKey());
		response.setQuestionType(setQuestion.getQuestionType());
		response.setAnswerVo(options);
		return response;
	}



	public Collection<QuestionResponse> loadQuestions() {
		Collection<Question> questions = questionRepo.findAll().stream().filter(qn -> qn.getIsAnswered() == false)
				.collect(Collectors.toList());
		log.info("Unanswered question from repo");
		Collection<QuestionResponse> responseOfList = new ArrayList<>();
		Collection<AnswerVo> responseOfAns = new ArrayList<>();
		questions.stream().forEach(qn -> {
			QuestionVo question = new QuestionVo();
			QuestionResponse response = new QuestionResponse();
			response.setQuestionId(qn.getId());
			response.setQuestionDescription(qn.getDescription());
			response.setTime(LocalDateTime.now());
			response.setIsAnswered(qn.getIsAnswered());
			response.setQuestionType(qn.getQuestionType());
			response.setSubject(qn.getSubject());
			AnswerVo findOptions = answerRepo.findByAnswerId(qn.getAnswerKey());
			responseOfAns.add(findOptions);
			response.setAnswerVo(responseOfAns);
			responseOfList.add(response);
		});
		log.info("Filtered  questions from repo");
		return responseOfList;
	}

	public String questions(AnswerRequest answer) {
			return null;
	}

}
