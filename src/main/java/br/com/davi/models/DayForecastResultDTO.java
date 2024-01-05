package br.com.davi.models;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DayForecastResultDTO {

	@Schema(name = "Air parameters", example = "humidity, temperature etc")
	private AirParametersDTO main;
	@Schema(name = "Date of the weather forecast")
	private String dt_txt;

	public DayForecastResultDTO() {
	}

	public DayForecastResultDTO(AirParametersDTO main, String dt_txt) {
		super();
		this.main = main;
		this.dt_txt = dt_txt;
	}

	public void setMain(AirParametersDTO main) {
		this.main = main;
	}

	public void setDt_txt(String dt_txt) {
		this.dt_txt = dt_txt;
	}

	@JsonAnyGetter
	public Map<String, Object> any() {
		return Map.of("airParameters", main,
				"forecastDate", dt_txt);
	}
}
