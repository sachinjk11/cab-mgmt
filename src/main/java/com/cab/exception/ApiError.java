package com.cab.exception;

public class ApiError {
	private String errorCode;
	private String errorMessage;

	public ApiError(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public ApiError() {
		super();
		this.errorCode = "";
		this.errorMessage = "";
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	@Override
	public String toString() {
		return String.format("ApiError [errorCode=%s, errorMessage=%s]",
				errorCode, errorMessage);
	}

	@Override
	public int hashCode() {
		return errorCode.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ApiError) {
			ApiError other = (ApiError) obj;
			if (errorCode.equals(other.errorCode)
					&& errorMessage.equals(other.errorMessage)) {
				return true;
			}
		}
		return false;
	}

}
