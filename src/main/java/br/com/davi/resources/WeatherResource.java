package br.com.davi.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.com.davi.WeatherRequestService;
import br.com.davi.model.RequestApiModel;
import br.com.davi.model.ResultWeatherDTO;

@RequestMapping("/api/weather")
@RestController
public class WeatherResource {

	@Autowired
	private WeatherRequestService weatherService;
	
	@PostMapping
	public ResponseEntity<ResultWeatherDTO> queryWeatherFromCity(@RequestBody RequestApiModel model) throws JsonMappingException, JsonProcessingException{
		return ResponseEntity.ok().body(weatherService.weatherFromCity(model));
	}
}
