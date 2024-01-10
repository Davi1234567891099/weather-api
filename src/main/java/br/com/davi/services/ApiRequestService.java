package br.com.davi.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.com.davi.models.RequestApiModel;
import br.com.davi.models.ResultForecastDTO;
import br.com.davi.models.ResultWeatherDTO;

public interface ApiRequestService {

	ResultWeatherDTO weatherFromCity(RequestApiModel request) throws JsonMappingException, JsonProcessingException;
	ResultForecastDTO forecastFromCity(RequestApiModel request) throws JsonMappingException, JsonProcessingException;
}
