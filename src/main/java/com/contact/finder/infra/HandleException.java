package com.contact.finder.infra;

import java.net.http.HttpHeaders;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.contact.finder.exception.CustomExceptionBadRequest;
import com.contact.finder.exception.CustomExceptionNoContent;
import com.contact.finder.exception.CustomExceptionResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class HandleException extends ResponseEntityExceptionHandler {

	@ExceptionHandler(CustomExceptionResponse.class)
	private ResponseEntity<ErrorResponse> exceptionNotFoundResponse(CustomExceptionResponse exception) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Contato não encontrado",
				exception.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	}
	
	
	@ExceptionHandler(CustomExceptionNoContent.class)
	private ResponseEntity<ErrorResponse> exceptionNotContetResponse(CustomExceptionNoContent exception) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NO_CONTENT.value(), "Conteudo vazio",
				exception.getMessage());
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(errorResponse);
	}
	
	@ExceptionHandler(CustomExceptionBadRequest.class)
	private ResponseEntity<ErrorResponse> exceptionBadRequestResponse(HttpMessageNotReadableException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("Erro ao ler a mensagem HTTP: {}", ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Requisição inválida",
                ex.getLocalizedMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }
	
	

}
