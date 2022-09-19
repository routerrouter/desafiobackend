package com.softexpert.desafiobackend.controller.exception;

import lombok.Getter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
import java.util.List;

@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(PaymentRequestException.class)
	public ResponseEntity<Object> handlePaymentRequestException(PaymentRequestException ex,
                                                                WebRequest request) {
		String userMessage = "Erro ao gerar Pagamento";
		String devMessage = ex.toString();
		List<StandardError> errors = Arrays.asList(new StandardError(userMessage, devMessage));
		return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler(StatusChangeException.class)
	public ResponseEntity<Object> handleChangedStatusException(StatusChangeException ex,
                                                               WebRequest request) {
		String userMessage = "Erro ao verificar status do Pagamento";
		String devMessage = ex.toString();
		List<StandardError> errors = Arrays.asList(new StandardError(userMessage, devMessage));
		return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	public static class StandardError {
		
		@Getter
		private String userMessage;
		@Getter
		private String devMessage;

		public StandardError(String userMessage, String devMessage) {
			this.userMessage = userMessage;
			this.devMessage = devMessage;
		}
	}
}
