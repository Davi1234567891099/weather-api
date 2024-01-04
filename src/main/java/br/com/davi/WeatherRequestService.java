package br.com.davi;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.davi.constants.WeatherConstants;
import br.com.davi.model.RequestApiModel;

@Service
public class WeatherRequestService {
	
	public String weatherFromCity(RequestApiModel request) {
		RestTemplate rstTemplate = new RestTemplate();
		return rstTemplate.getForEntity(WeatherConstants.API_URL, String.class, buildUriParams(request)).getBody();
	}
	
	private static Map<String, String> buildUriParams(RequestApiModel request){
		Map<String, String> params = new HashMap<>();
		params.put("cityName", request.getCityName());
		params.put("mode", request.getMode());
		params.put("weatherApiKey", request.getWeatherApiKey());
		return params;
	}
}
