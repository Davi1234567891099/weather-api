package br.com.davi.models;

import java.time.Instant;
import java.util.Date;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({ "name", "main", "date" })
public class ResultWeatherDTO {

	private static final long THREE_HOURS = 10800;
	
	private String name;
	private AirParametersDTO main;
	private Date date = Date.from(Instant.now().minusSeconds(THREE_HOURS));
	
	public ResultWeatherDTO() {
	}

	public ResultWeatherDTO(String name, AirParametersDTO main, Long dt) {
		super();
		this.name = name;
		this.main = main;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setMain(AirParametersDTO main) {
		this.main = main;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@JsonAnyGetter
	public Map<String, Object> any() {
		return Map.of("cityName", name,
				"airParameters", main,
				"actualDate", date);
	}
}
