package br.com.davi.resources;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.com.davi.enums.Unit;
import br.com.davi.models.RequestApiModel;
import br.com.davi.models.ResultForecastDTO;
import br.com.davi.models.ResultWeatherDTO;
import br.com.davi.services.ApiRequestService;

@ExtendWith(MockitoExtension.class)
public class WeatherResourceTest {

	@InjectMocks
	private WeatherResource resource;
	
	@Mock
	private ApiRequestService weatherService;
	
	private RequestApiModel requestApiModel;
	
	@BeforeEach
	void before() {
		requestApiModel = new RequestApiModel("city", "key", Unit.METRIC);
	}
	
	@Test
	void testQueryWeatherFromCity() throws JsonMappingException, JsonProcessingException {
		when(weatherService.weatherFromCity(requestApiModel)).thenReturn(new ResultWeatherDTO());
		var output = resource.queryWeatherFromCity(requestApiModel);
		Assertions.assertNotNull(output);
	}
	
	@Test
	void testQueryForecastFromCity() throws JsonMappingException, JsonProcessingException {
		when(weatherService.forecastFromCity(requestApiModel)).thenReturn(new ResultForecastDTO());
		var output = resource.queryForecastFromCity(requestApiModel);
		Assertions.assertNotNull(output);
	}
}
