package com.hackerrank.weather.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackerrank.weather.model.Weather;
import com.hackerrank.weather.repository.WeatherRepository;

@Service("WeatherService")
public class WeatherImpl implements WeatherService {
	
	@Autowired
	WeatherRepository repository;

	@Override
	public Weather altaClima(Weather in) throws Exception {
		try {
			return repository.save(in);
		}catch (Exception e) {
			throw new Exception(this.getClass().getName() + " : " + e.getLocalizedMessage());
		}		
	}

	@Override
	public List<Weather> getClima() throws Exception {
		try {
			return repository.findAll();
		}catch (Exception e) {
			throw new Exception(this.getClass().getName() + " : " + e.getLocalizedMessage());
		}	
	}

	@Override
	public Weather getClima(Integer id) throws Exception{
		try {
			Optional<Weather> out = repository.findById(id);
			if(out.isPresent()) {
				return out.get();
			}
		}catch (Exception e) {
			throw new Exception(this.getClass().getName() + " : " + e.getLocalizedMessage());
		}	
		return null;
	}

	@Override
	public int deleteClima(Integer id) throws Exception {
		try {
			Optional<Weather> out = repository.findById(id);
			if(out.isPresent()) {
				repository.deleteById(id);
				return 1;
			}
		}catch (Exception e) {
			throw new Exception(this.getClass().getName() + " : " + e.getLocalizedMessage());
		}	
		return 0;
	}
}