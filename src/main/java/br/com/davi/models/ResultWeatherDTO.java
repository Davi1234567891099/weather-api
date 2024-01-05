package br.com.davi.models;

import java.time.Instant;
import java.util.Date;
import java.util.Map;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.v3.oas.annotations.media.Schema;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({ "name", "main", "date" })
public class ResultWeatherDTO extends RepresentationModel<ResultWeatherDTO>{

	private static final long THREE_HOURS = 10800;
	
	@Schema(name = "name of the city")
	private String name;
	@Schema(name = "Air parameters", example = "humidity, temperature etc")
	private AirParametersDTO main;
	@Schema(name = "date the request was made")
	private Date date = Date.from(Instant.now().minusSeconds(THREE_HOURS));
	
	public ResultWeatherDTO() {
	}

	public ResultWeatherDTO(String name, AirParametersDTO main) {
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
