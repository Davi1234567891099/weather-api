package br.com.davi.model;

import java.io.Serializable;
import java.util.Objects;

public class RequestApiModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String cityName;
	private String mode;
	private String weatherApiKey;

	public RequestApiModel(String cityName, String mode, String weatherApiKey) {
		super();
		this.cityName = cityName;
		this.mode = mode;
		this.weatherApiKey = weatherApiKey;
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

	@Override
	public int hashCode() {
		return Objects.hash(cityName, mode, weatherApiKey);
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
		return Objects.equals(cityName, other.cityName) && Objects.equals(mode, other.mode)
				&& Objects.equals(weatherApiKey, other.weatherApiKey);
	}
}
