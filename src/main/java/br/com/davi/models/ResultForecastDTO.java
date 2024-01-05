package br.com.davi.models;

import java.util.List;
import java.util.Map;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultForecastDTO extends RepresentationModel<ResultForecastDTO>{

	@Schema(name = "multi-day weather forecast list")
	private List<DayForecastResultDTO> list;
	@Schema(name = "City and country informations")
	private CityDTO city;

	public ResultForecastDTO() {
	}

	public ResultForecastDTO(List<DayForecastResultDTO> list, CityDTO city) {
		super();
		this.list = list;
		this.city = city;
	}

	public void setList(List<DayForecastResultDTO> list) {
		this.list = list;
	}

	public void setCity(CityDTO city) {
		this.city = city;
	}

	@JsonAnyGetter
	public Map<String, Object> any() {
		return Map.of("forecastInfo", list,
				"city", city);
	}
}
