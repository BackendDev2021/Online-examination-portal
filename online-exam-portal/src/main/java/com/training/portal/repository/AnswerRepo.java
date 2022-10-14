package com.training.portal.repository;

import com.training.portal.model.Answer;
import com.training.portal.model.AnswerVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface AnswerRepo extends JpaRepository<AnswerVo, Long>{

    AnswerVo findByAnswerId(String answerKey);
}
