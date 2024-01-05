package br.com.davi.models;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AirParametersDTO {

	private Double temp;
	private Double feels_like;
	private Integer humidity;
	
	public AirParametersDTO() {
	}

	public AirParametersDTO(Double temp, Double feels_like, Integer humidity) {
		super();
		this.temp = temp;
		this.feels_like = feels_like;
		this.humidity = humidity;
	}

	public void setTemp(Double temp) {
		this.temp = temp;
	}

	public void setFeels_like(Double feels_like) {
		this.feels_like = feels_like;
	}

	public void setHumidity(Integer humidity) {
		this.humidity = humidity;
	}

	@JsonAnyGetter
	public Map<String, Object> any() {
		return Map.of("temperature", temp,
				"feelsLike", feels_like,
				"humidity", humidity);
	}
}
