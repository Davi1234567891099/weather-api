package br.com.davi.exceptions.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;

import jakarta.servlet.http.HttpServletRequest;

@ExtendWith(MockitoExtension.class)
public class ResourceExceptionHandlerTest {

	private static final String ERROR_MESSAGE = "error message";
	private static final int INTERNAL_SERVER_ERROR_HTTP_CODE = 500;
	private static final int UNAUTHORIZED_HTTP_CODE = 401;
	
	@InjectMocks
	private ResourceExceptionHandler exceptionHandler;
	
	@Mock
	private HttpServletRequest servletRequest;
	
	@Test
	void testHandleAllExceptions() {
		var output = exceptionHandler.handleAllExceptions(new Exception(ERROR_MESSAGE), servletRequest);
		assertEquals("Error occurred when trying to process the request", output.getBody().getError());
		assertEquals(ERROR_MESSAGE, output.getBody().getMessage());
		assertEquals(HttpStatusCode.valueOf(INTERNAL_SERVER_ERROR_HTTP_CODE), output.getStatusCode());
	}
	
	@Test
	void testHandleUnauthorizedExceptions() {
		var output = exceptionHandler.handleUnauthorizedExceptions(new Exception(ERROR_MESSAGE), servletRequest);
		assertEquals("Error", output.getBody().getError());
		assertEquals(ERROR_MESSAGE, output.getBody().getMessage());
		assertEquals(HttpStatusCode.valueOf(UNAUTHORIZED_HTTP_CODE), output.getStatusCode());
	}
}
