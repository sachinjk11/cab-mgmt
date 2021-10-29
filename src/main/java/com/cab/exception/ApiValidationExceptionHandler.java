package com.cab.exception;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiValidationExceptionHandler extends
		ResponseEntityExceptionHandler {
	private final Logger LOGGER = LoggerFactory
			.getLogger(ApiValidationExceptionHandler.class);

	@Autowired
	private MessageSource msgSource;

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		BindingResult bindingResult = ex.getBindingResult();
		List<FieldError> apiFieldErrors = bindingResult.getFieldErrors();

		List<ApiError> apiErrorList = new ArrayList<ApiError>();
		for (FieldError fieldError : apiFieldErrors) {

			String errorCode = fieldError.getDefaultMessage();
			String errorMessage = msgSource.getMessage(errorCode, null, null);
			ApiError apiError = new ApiError(errorCode, errorMessage);

			LOGGER.info("Api Validation Error " + apiError);
			apiErrorList.add(apiError);
		}

		Object response = apiErrorList;
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ServiceException.class})
	protected ResponseEntity<Object> handleServiceException(
			HttpServletResponse response, ServiceException ex) {

		List<ApiError> apiErrorList = new ArrayList<ApiError>();
		ServiceException serviceException = (ServiceException) ex;
		Map<String, String> errorMap = new HashMap<String, String>();
		errorMap.put(serviceException.getClass().toString(), serviceException.getMessage());

		for (String key : errorMap.keySet()) {
			String errorCode = key;
			String errorMessage = errorMap.get(key);
			
			ApiError apiError = new ApiError(errorCode, errorMessage);

			LOGGER.info("Service Exception " + apiError);
			apiErrorList.add(apiError);
		}

		Object responseObj = apiErrorList;
		return new ResponseEntity<>(responseObj,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(Exception.class)
	protected ResponseEntity<Object> handleException(
			HttpServletResponse response, Exception ex) {
	    LOGGER.error("Exception " , ex);
		List<ApiError> apiErrorList = new ArrayList<ApiError>();
		ApiError genericApiError = CommonErrors.API_INTERNAL_ERROR.get();
		apiErrorList.add(genericApiError);

		Object responseObj = apiErrorList;
		LOGGER.info("Setting Error in Response " + apiErrorList);
		return new ResponseEntity<>(responseObj,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

}