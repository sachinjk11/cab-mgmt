package com.cab.exception;

public enum CommonErrors {
	API_AUTHENTICATION_ERROR("api.authentication.error", "Credentials provided by you is incorrect"),
	API_AUTHORISATION_ERROR("api.authorisation.error", "User does not have access to resource"),
	API_INTERNAL_ERROR("api.interal.error","Internal Error Occured.");

	String errorCode;
	String errorMessage;

	private CommonErrors(String errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public ApiError get() {
		ApiError genericApiError = new ApiError(this.errorCode,
				this.errorMessage);
		return genericApiError;
	}
}