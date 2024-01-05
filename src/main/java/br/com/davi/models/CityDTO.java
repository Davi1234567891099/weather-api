package br.com.davi.models;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CityDTO {

	private String name;
	private String country;
	private Long population;

	public CityDTO() {
	}

	public CityDTO(String name, String country, Long population) {
		this.name = name;
		this.country = country;
		this.population = population;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setPopulation(Long population) {
		this.population = population;
	}

	@JsonAnyGetter
	public Map<String, Object> any() {
		return Map.of("cityName", name,
				"country", country,
				"population", population);
	}
}
