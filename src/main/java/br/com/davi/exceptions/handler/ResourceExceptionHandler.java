package br.com.davi.exceptions.handler;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.auth0.jwt.exceptions.TokenExpiredException;

import br.com.davi.exceptions.ExceptionResponse;
import br.com.davi.exceptions.ExternalApiException;
import br.com.davi.exceptions.InvalidAuthRequestException;
import br.com.davi.exceptions.InvalidJwtAuthenticationException;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, HttpServletRequest request) {
		String error = "Error occurred when trying to process the request";
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		ExceptionResponse err = new ExceptionResponse(Instant.now(), status.value(), error, ex.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(ExternalApiException.class)
	public ResponseEntity<ExceptionResponse> handleUnauthorizedExceptions(Exception ex, HttpServletRequest request){
		String error = "Error";
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		ExceptionResponse err = new ExceptionResponse(Instant.now(), status.value(), error, ex.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(InvalidJwtAuthenticationException.class)
	public ResponseEntity<ExceptionResponse> handleInvalidJwtAuthenticationExceptions(Exception ex, HttpServletRequest request) {
		String error = "Invalid token";
		HttpStatus status = HttpStatus.FORBIDDEN;
		ExceptionResponse err = new ExceptionResponse(Instant.now(), status.value(), error, ex.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(InvalidAuthRequestException.class)
	public ResponseEntity<ExceptionResponse> handleInvalidAuthRequestException(Exception ex, HttpServletRequest request) {
		String error = "Error when trying to validate the authentication";
		HttpStatus status = HttpStatus.FORBIDDEN;
		ExceptionResponse err = new ExceptionResponse(Instant.now(), status.value(), error, ex.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(TokenExpiredException.class)
	public ResponseEntity<ExceptionResponse> handleTokenExpiredException(Exception ex, HttpServletRequest request) {
		String error = "Token expired!";
		HttpStatus status = HttpStatus.FORBIDDEN;
		ExceptionResponse err = new ExceptionResponse(Instant.now(), status.value(), error, ex.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
}
