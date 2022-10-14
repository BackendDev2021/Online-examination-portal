package com.training.portal.service;

import com.training.portal.model.Answer;
import com.training.portal.model.AnswerVo;
import com.training.portal.model.Question;
import com.training.portal.model.SetQuestionVo;
import com.training.portal.repository.AnswerRepo;
import com.training.portal.repository.QuestionRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class QuestionService {

	@Autowired
	private QuestionRepo questionRepo;
	
	@Autowired
	private AnswerRepo answerRepo;

	public  Question  addQuestion(Question question) {
		question.setDate(LocalDate.now());
		question.setIsAnswered(false);
		question.setSubjectId(generateSubjectId(question.getSubject()));
		question.setAnswerKey(answerKeyId(question));
		Question returnReponse = questionRepo.save(question);
		log.info("Question saved to repo");
		return returnReponse;
	}


    public Page<Question> allQuestionsWithAnswer(Integer pageNo, Integer offset) {
		Pageable paging = PageRequest.of(pageNo, offset ==null ? 10 :offset);
		log.info("Paging request to access the question for page - {},size-{}",paging.getPageNumber(),paging.getPageNumber());
		Page<Question> questions =  questionRepo.findAll(paging);
		questions.stream().sorted(Comparator.comparing(Question::getId));
		log.info("Sorted and filtered question final response from repo");
		return questions;
    }

	private String generateSubjectId(String subject){
		return subject.substring(0, 3) +(questionRepo.count()+1);
	}

	private String answerKeyId(Question qn){
		return "A"+questionRepo.count()+1+qn.getSubject();
	}
}
