package br.com.davi.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.com.davi.models.RequestApiModel;
import br.com.davi.models.ResultForecastDTO;
import br.com.davi.models.ResultWeatherDTO;
import br.com.davi.services.WeatherRequestService;

@RequestMapping("/api/")
@RestController
public class WeatherResource {

	@Autowired
	private WeatherRequestService weatherService;

	@PostMapping(value = "weather",
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
			consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ResultWeatherDTO> queryWeatherFromCity(@RequestBody RequestApiModel model) throws JsonMappingException, JsonProcessingException {
		return ResponseEntity.ok().body(weatherService.weatherFromCity(model));
	}

	@PostMapping(value = "forecast",
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
			consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ResultForecastDTO> queryForecastFromCity(@RequestBody RequestApiModel model)
			throws JsonMappingException, JsonProcessingException {
		return ResponseEntity.ok().body(weatherService.forecastFromCity(model));
	}
}
