package com.se.ReminderManagement.exception;

import java.util.List;

public class ValidationErrorResponse {
	private List<ValidationError> errors;

	public ValidationErrorResponse(List<ValidationError> errors) {
		this.errors = errors;
	}

	public List<ValidationError> getErrors() {
		return errors;
	}

}
