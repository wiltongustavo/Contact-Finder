package com.contact.finder.infra;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.contact.finder.exception.CustomExceptionResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class HandleException extends ResponseEntityExceptionHandler{

	
	@ExceptionHandler(CustomExceptionResponse.class)
	private ResponseEntity<String> customExceptionResponse(CustomExceptionResponse exception){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contato não encontrado");
	}
	
}
