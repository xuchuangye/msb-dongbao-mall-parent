package com.msb.dongbao.portal.web.advice;

import com.msb.dongbao.common.base.response.ResponseResult;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author xcy
 * @date 2022/9/14 - 8:48
 */
@ControllerAdvice
public class AdviceHandler extends ResponseEntityExceptionHandler{

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		StringBuilder stringBuilder = new StringBuilder();
		for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
			String defaultMessage = fieldError.getDefaultMessage();
			stringBuilder.append("      ").append(defaultMessage);
			break;
		}

		return new ResponseEntity(
				ResponseResult.builder().code(102).message(stringBuilder.toString()).build(),
				HttpStatus.OK);
	}
}
