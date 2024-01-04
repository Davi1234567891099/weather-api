package br.com.davi.model;

import java.io.Serializable;
import java.util.Objects;

import br.com.davi.enums.Unit;

public class RequestApiModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String cityName;
	private String mode;
	private String weatherApiKey;
	private Unit unit;
	
	public RequestApiModel(String cityName, String mode, String weatherApiKey, Unit unit) {
		super();
		this.cityName = cityName;
		this.mode = mode;
		this.weatherApiKey = weatherApiKey;
		this.unit = unit;
	}

	public RequestApiModel() {
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getWeatherApiKey() {
		return weatherApiKey;
	}

	public void setWeatherApiKey(String weatherApiKey) {
		this.weatherApiKey = weatherApiKey;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cityName, mode, unit, weatherApiKey);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RequestApiModel other = (RequestApiModel) obj;
		return Objects.equals(cityName, other.cityName) && Objects.equals(mode, other.mode) && unit == other.unit
				&& Objects.equals(weatherApiKey, other.weatherApiKey);
	}
}
