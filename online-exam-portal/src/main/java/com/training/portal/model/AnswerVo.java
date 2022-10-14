package com.training.portal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="answerVo")
public class AnswerVo {

	@Id
	@GeneratedValue
	private Long id;

	private Long questionId;

	private String answerId;

	private String answer;

	private Boolean isCorrectAnswer;
}
