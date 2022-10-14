package com.training.portal.model;

import com.training.portal.utils.QuestionType;
import lombok.Data;
import lombok.Getter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;
import java.util.Collection;

@Data
public class QuestionResponse {

    private Long questionId;

    private Boolean IsAnswered;

    private String questionDescription;

    private String subject;

    @Enumerated(EnumType.STRING)
    private QuestionType questionType;

    private Collection<AnswerVo> answerVo;

    private LocalDateTime time;
}
