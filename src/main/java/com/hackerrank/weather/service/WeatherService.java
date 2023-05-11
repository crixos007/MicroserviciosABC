package com.hackerrank.weather.service;

import java.util.List;

import com.hackerrank.weather.model.Weather;

public interface WeatherService {
	public Weather altaClima(Weather in)throws Exception;
	public List<Weather> getClima()throws Exception;
	public Weather getClima(Integer id)throws Exception;
	public int deleteClima(Integer id) throws Exception;
}