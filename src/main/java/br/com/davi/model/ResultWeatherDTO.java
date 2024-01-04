package br.com.davi.model;

import java.time.Instant;
import java.util.Date;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({ "cityName", "airParameters" })
public class ResultWeatherDTO {

	private String name;
	private AirParameters main;

	public ResultWeatherDTO() {
	}

	public ResultWeatherDTO(String name, AirParameters main, Long dt) {
		super();
		this.name = name;
		this.main = main;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setMain(AirParameters main) {
		this.main = main;
	}


	@JsonAnyGetter
	public Map<String, Object> any() {
		return Map.of("cityName", name,
				"airParameters", main);
	}
}
