package br.com.davi.model;

import java.util.Map;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AirParameters {

	private Double temp;
	private Double feels_like;
	private Integer humidity;
	
	public AirParameters() {
	}

	public AirParameters(Double temp, Double feels_like, Integer humidity) {
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

	@Override
	public int hashCode() {
		return Objects.hash(temp);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AirParameters other = (AirParameters) obj;
		return Objects.equals(temp, other.temp);
	}

	@JsonAnyGetter
	public Map<String, Object> any() {
		return Map.of("temperature", temp,
				"feelsLike", feels_like,
				"humidity", humidity);
	}
}
