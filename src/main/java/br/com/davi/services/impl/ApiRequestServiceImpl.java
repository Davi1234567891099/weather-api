package br.com.davi.services.impl;

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
import br.com.davi.services.ApiRequestService;

@Service
public class ApiRequestServiceImpl implements ApiRequestService{
	
	public ResultWeatherDTO weatherFromCity(RequestApiModel request) throws JsonMappingException, JsonProcessingException {
		return executeWeatherRequest(request);
	}

	public ResultForecastDTO forecastFromCity(RequestApiModel request) throws JsonMappingException, JsonProcessingException {
		return executeForecastRequest(request);
	}
	
	private ResultForecastDTO executeForecastRequest(RequestApiModel request) throws JsonMappingException, JsonProcessingException {
		var dto = makeRequest(request, ResultForecastDTO.class, WeatherConstants.FORECAST_API_URL);
		setForecastHateoasLinks(request, dto);
		return dto;
	}

	private ResultWeatherDTO executeWeatherRequest(RequestApiModel request) throws JsonMappingException, JsonProcessingException {
		var dto = makeRequest(request, ResultWeatherDTO.class, WeatherConstants.WEATHER_API_URL);
		setWeatherHateoasLinks(request, dto);
		return dto;
	}
	
	private void setForecastHateoasLinks(RequestApiModel request, ResultForecastDTO dto) throws JsonMappingException, JsonProcessingException {
		dto.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(WeatherResource.class).queryWeatherFromCity(request)).withSelfRel());
	}
	
	private void setWeatherHateoasLinks(RequestApiModel request, ResultWeatherDTO dto) throws JsonMappingException, JsonProcessingException {
		dto.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(WeatherResource.class).queryForecastFromCity(request)).withSelfRel());
	}

	private <T> T makeRequest(RequestApiModel request, Class<T> dtoModel, String apiUrl) throws JsonMappingException, JsonProcessingException {
		String json = tryConsumeApi(request, apiUrl);
		return deserializeJson(dtoModel, json);
	}

	private <T> T deserializeJson(Class<T> dtoModel, String json) throws JsonProcessingException, JsonMappingException {
		var mapper = new ObjectMapper();
		var dto = mapper.readValue(json, dtoModel);
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
