package com.simon.arpe.ezyweather.otto.weather;

public class WeatherEvent {
	
	String result;
	
	public WeatherEvent(String event){
		this.result = event;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	

}
