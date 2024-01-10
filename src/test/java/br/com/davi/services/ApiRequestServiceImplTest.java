package br.com.davi.services;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.com.davi.enums.Unit;
import br.com.davi.exceptions.ExternalApiException;
import br.com.davi.models.RequestApiModel;
import br.com.davi.services.impl.ApiRequestServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ApiRequestServiceImplTest {

	@InjectMocks
	private ApiRequestServiceImpl apiRequestService;
	
	@Test
	void testWeatherFromCityWithWrongApiKeyShouldThrowException() throws JsonMappingException, JsonProcessingException {
		assertThrows(ExternalApiException.class, () -> apiRequestService.weatherFromCity(new RequestApiModel("Curitiba", "wrong api key", Unit.METRIC)));
	}
	
	@Test
	void testForecastFromCityWithWrongApiKeyShouldThrowException() throws JsonMappingException, JsonProcessingException {
		assertThrows(ExternalApiException.class, () -> apiRequestService.forecastFromCity(new RequestApiModel("Curitiba", "wrong api key", Unit.METRIC)));
	}
}
