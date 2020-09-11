package com.example.TesteApiNew.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;


public class Historico {
	
	private String latitude;
	
	private String longitude;
	
	private String temperaturaMax;
	
	private String temperaturaMin;

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getTemperaturaMax() {
		return temperaturaMax;
	}

	public void setTemperaturaMax(String temperaturaMax) {
		this.temperaturaMax = temperaturaMax;
	}

	public String getTemperaturaMin() {
		return temperaturaMin;
	}

	public void setTemperaturaMin(String temperaturaMin) {
		this.temperaturaMin = temperaturaMin;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Historico historico = (Historico) o;
		return Objects.equals(latitude, historico.latitude) &&
				Objects.equals(longitude, historico.longitude) &&
				Objects.equals(temperaturaMax, historico.temperaturaMax) &&
				Objects.equals(temperaturaMin, historico.temperaturaMin);
	}

	@Override
	public int hashCode() {
		return Objects.hash(latitude, longitude, temperaturaMax, temperaturaMin);
	}

	@Override
	public String toString() {
		return "Historico{" +
				"latitude='" + latitude + '\'' +
				", longitude='" + longitude + '\'' +
				", temperaturaMax='" + temperaturaMax + '\'' +
				", temperaturaMin='" + temperaturaMin + '\'' +
				'}';
	}
}
