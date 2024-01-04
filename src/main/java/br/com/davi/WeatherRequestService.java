package br.com.davi;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.davi.constants.WeatherConstants;
import br.com.davi.model.RequestApiModel;
import br.com.davi.model.ResultWeatherDTO;

@Service
public class WeatherRequestService {
	
	public ResultWeatherDTO weatherFromCity(RequestApiModel request) throws JsonMappingException, JsonProcessingException {
		return makeRequest(request);
	}

	private ResultWeatherDTO makeRequest(RequestApiModel request) throws JsonMappingException, JsonProcessingException {
		RestTemplate rstTemplate = new RestTemplate();
		var json = rstTemplate.getForEntity(WeatherConstants.WEATHER_API_URL, String.class, buildUriParams(request)).getBody();
		
		var mapper = new ObjectMapper();
		return mapper.readValue(json, ResultWeatherDTO.class);
	}
	
	private static Map<String, String> buildUriParams(RequestApiModel request){
		Map<String, String> params = new HashMap<>();
		params.put("cityName", request.getCityName());
		params.put("mode", request.getMode());
		params.put("weatherApiKey", request.getWeatherApiKey());
		params.put("units", request.getUnit().toString());
		return params;
	}
}
