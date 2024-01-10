package br.com.davi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class InvalidAuthRequestException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidAuthRequestException(String message) {
		super(message);
	}
	
	public InvalidAuthRequestException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
