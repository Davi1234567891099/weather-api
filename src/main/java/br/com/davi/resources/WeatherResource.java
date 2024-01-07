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
import br.com.davi.services.ApiRequestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RequestMapping("/api/")
@RestController
public class WeatherResource {

	@Autowired
	private ApiRequestService weatherService;

	@PostMapping(value = "weather",
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
			consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@Operation(summary = "Shows the weather from a city", description = "Shows the weather from a city", tags = { "Weather" })
	@ApiResponses(value = { 
			@ApiResponse(description = "Success", responseCode = "200", content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ResultWeatherDTO.class))) }),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content), })
	public ResponseEntity<ResultWeatherDTO> queryWeatherFromCity(@RequestBody RequestApiModel model) throws JsonMappingException, JsonProcessingException {
		return ResponseEntity.ok().body(weatherService.weatherFromCity(model));
	}

	
	@PostMapping(value = "forecast",
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
			consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@Operation(summary = "Shows the forecast weather from a city", description = "Show the forecast weather from a city", tags = { "Forecast" })
	@ApiResponses(value = { 
			@ApiResponse(description = "Success", responseCode = "200", content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ResultForecastDTO.class))) }),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content), })
	public ResponseEntity<ResultForecastDTO> queryForecastFromCity(@RequestBody RequestApiModel model)
			throws JsonMappingException, JsonProcessingException {
		return ResponseEntity.ok().body(weatherService.forecastFromCity(model));
	}
}
