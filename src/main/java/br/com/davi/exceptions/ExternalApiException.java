package br.com.davi.exceptions;

public class ExternalApiException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ExternalApiException(String message) {
		super(message);
	}
	
	public ExternalApiException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
