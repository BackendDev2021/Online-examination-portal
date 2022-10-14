package com.training.portal.model;

import lombok.Data;

@Data
public class AnswerRequest {

	private Long questionId;
	
	private String subjectId;
	
	private String answerId;
}
