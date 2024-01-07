package br.com.davi.config;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;

@ExtendWith(MockitoExtension.class)
public class WebConfigTest {

	@InjectMocks
	private WebConfig webConfig;
	
	@Test
	void testConfigureContentNegotiation() {
		assertDoesNotThrow(() -> webConfig.configureContentNegotiation(new ContentNegotiationConfigurer(null)));
	}
}
