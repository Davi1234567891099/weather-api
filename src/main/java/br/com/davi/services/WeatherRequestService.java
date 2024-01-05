package br.com.davi.services;

import static java.lang.String.format;

import java.util.HashMap;
import java.util.Map;

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.davi.constants.WeatherConstants;
import br.com.davi.exceptions.ExceptionMessages;
import br.com.davi.exceptions.ExternalApiException;
import br.com.davi.models.RequestApiModel;
import br.com.davi.models.ResultForecastDTO;
import br.com.davi.models.ResultWeatherDTO;
import br.com.davi.resources.WeatherResource;

@Service
public class WeatherRequestService {
	
	public ResultWeatherDTO weatherFromCity(RequestApiModel request) throws JsonMappingException, JsonProcessingException {
		return makeWeatherRequest(request);
	}

	public ResultForecastDTO forecastFromCity(RequestApiModel request) throws JsonMappingException, JsonProcessingException {
		return makeForecastRequest(request);
	}
	
	private ResultForecastDTO makeForecastRequest(RequestApiModel request) throws JsonMappingException, JsonProcessingException {
		String json = tryConsumeApi(request, WeatherConstants.FORECAST_API_UTL);
		
		var mapper = new ObjectMapper();
		var dto = mapper.readValue(json, ResultForecastDTO.class);
		dto.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(WeatherResource.class).queryWeatherFromCity(request)).withSelfRel());
		return dto;
	}
	
	private ResultWeatherDTO makeWeatherRequest(RequestApiModel request) throws JsonMappingException, JsonProcessingException {
		String json = tryConsumeApi(request, WeatherConstants.WEATHER_API_URL);
		
		var mapper = new ObjectMapper();
		var dto = mapper.readValue(json, ResultWeatherDTO.class);
		dto.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(WeatherResource.class).queryForecastFromCity(request)).withSelfRel());
		return dto;
	}

	private String tryConsumeApi(RequestApiModel request, String apiUrl) {
		String json = null;
		RestTemplate rstTemplate = new RestTemplate();
		try {
			json = rstTemplate.getForEntity(apiUrl, String.class, buildUriParams(request)).getBody();
		} catch(RuntimeException e) {
			throw new ExternalApiException(format(ExceptionMessages.API_CONSUMPTION_ERROR, e.getMessage()), e);
		}
		return json;
	}
	
	private static Map<String, String> buildUriParams(RequestApiModel request){
		Map<String, String> params = new HashMap<>();
		params.put("cityName", request.getCityName());
		params.put("apiKey", request.getApiKey());
		params.put("units", request.getUnit().toString());
		return params;
	}
}
