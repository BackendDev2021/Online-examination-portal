package com.training.portal.exception;

public enum ErrorDetails {

	// User Error codes
	VALUES_NOT_FOUND("", "Please enter the values", "values_not_found"),
	REQUEST_VALUE_NOT_FOUND("", "Value cannot be blank", "request_value_not_found"),
	RESOURCE_NOT_FOUND("", "Requested resource not found", "resource_not_found"),
	WRONG_ANSWER("001", "Wrong answer...!", "wrong_answer_error"),
	 USER_NOT_FOUND ("002","User not found","user_not_found"),
	USER_EXIST("003","User already exist with same Email" , "user_already_exist");
    private final String code;
	private final String message;
	private final String uiErrorKey;

	private ErrorDetails(String code, String description, String uiErrorKey) {
		this.code = code;
		this.message = description;
		this.uiErrorKey = uiErrorKey;
	}

	public String getMessage() {
		return message;
	}

	public String getCode() {
		return code;
	}

	public String getUiErrorKey() {
		return uiErrorKey;
	}

}
