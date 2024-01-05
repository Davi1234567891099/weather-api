package br.com.davi.models;

import java.io.Serializable;
import java.util.Objects;

import br.com.davi.enums.Unit;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

public class RequestApiModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(name = "cityName", example = "London", requiredMode = RequiredMode.REQUIRED)
	private String cityName;
	@Schema(name = "apiKey", example = "478067e1f52740cd848312fb7b4611f4", requiredMode = RequiredMode.REQUIRED)
	private String apiKey;
	@Schema(name = "unit", example = "IMPERIAL or METRIC", requiredMode = RequiredMode.REQUIRED)
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
