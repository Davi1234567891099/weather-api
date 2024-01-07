package br.com.davi.config;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class OpenApiConfigTest {

	@InjectMocks
	private OpenApiConfig config;
	
	@Test
	void testCustomOpenApi() {
		var output = config.customOpenApi();
		assertNotNull(output);
	}
}
