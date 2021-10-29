package com.cab.exception;

public class NoOngoingTripException extends ServiceException {

	public NoOngoingTripException(String error) {
		super(error);
	}
}
