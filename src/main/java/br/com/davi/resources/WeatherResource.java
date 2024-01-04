package br.com.davi.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.davi.WeatherRequestService;
import br.com.davi.model.RequestApiModel;

@RequestMapping("/api/weather/v1")
@RestController
public class WeatherResource {

	@Autowired
	private WeatherRequestService weatherService;
	
	@PostMapping
	public ResponseEntity<String> queryWeatherFromCity(@RequestBody RequestApiModel model){
		return ResponseEntity.ok().body(weatherService.weatherFromCity(model));
	}
}
