package br.com.davi.models;

import java.io.Serializable;
import java.util.Objects;

import br.com.davi.enums.Unit;

public class RequestApiModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String cityName;
	private String apiKey;
	private Unit unit;

	public RequestApiModel() {
	}

	public RequestApiModel(String cityName, String apiKey, Unit unit) {
		super();
		this.cityName = cityName;
		this.apiKey = apiKey;
		this.unit = unit;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	@Override
	public int hashCode() {
		return Objects.hash(apiKey, cityName, unit);
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
		return Objects.equals(apiKey, other.apiKey) && Objects.equals(cityName, other.cityName) && unit == other.unit;
	}
}
