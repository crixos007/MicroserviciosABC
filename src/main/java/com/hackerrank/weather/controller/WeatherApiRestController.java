package com.hackerrank.weather.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;

import com.hackerrank.weather.model.Weather;
import com.hackerrank.weather.service.WeatherService;

@CrossOrigin(origins = "*")
@RestController()
public class WeatherApiRestController {
	private static final Logger LOG = LogManager.getLogger(WeatherApiRestController.class.getName());
	
	@Autowired
	WeatherService service;
	
	@PostMapping("/weather")
	public ResponseEntity<Weather> altaClima(@RequestBody Weather in) {
		Date date = new Date();		
    	String idTraza = (int)(Math.random()*30+1) + date.getTime() + "_LOG ";	
    	Weather out = new Weather();
		
		//LOGICA DEL SERVICIO
		try {	
			try {
				out = service.altaClima(in);
			}catch (Exception e) {
				LOG.error(idTraza +" ERROR MSG: " + e.getLocalizedMessage());
				throw new HttpClientErrorException(HttpStatus.SERVICE_UNAVAILABLE);
			}
			return new ResponseEntity<Weather>(out,HttpStatus.CREATED);
		}
		catch(HttpStatusCodeException exception) {
			LOG.error(idTraza +" ERROR MSG: " + exception.getLocalizedMessage());
			HttpHeaders headers=new HttpHeaders();
			headers.add("error", exception.getLocalizedMessage());
			headers.add("errorDesc", exception.getResponseBodyAsString());
			return new ResponseEntity<Weather>(out,headers,exception.getStatusCode());
		}
	}

	@GetMapping("/weather")
	public ResponseEntity<List<Weather>> clima() {
		Date date = new Date();		
    	String idTraza = (int)(Math.random()*30+1) + date.getTime() + "_LOG ";	
    	List<Weather> out = new ArrayList<Weather>();
		
		//LOGICA DEL SERVICIO
		try {	
			try {
				out = service.getClima();
			}catch (Exception e) {
				LOG.error(idTraza +" ERROR MSG: " + e.getLocalizedMessage());
				throw new HttpClientErrorException(HttpStatus.SERVICE_UNAVAILABLE);
			}
			return new ResponseEntity<List<Weather>>(out,HttpStatus.OK);
		}
		catch(HttpStatusCodeException exception) {
			LOG.error(idTraza +" ERROR MSG: " + exception.getLocalizedMessage());
			HttpHeaders headers=new HttpHeaders();
			headers.add("error", exception.getLocalizedMessage());
			headers.add("errorDesc", exception.getResponseBodyAsString());
			return new ResponseEntity<List<Weather>>(out,headers,exception.getStatusCode());
		}
	}
	
	@GetMapping("/weather/{id}")
	public ResponseEntity<Weather> clima(@PathVariable("id") Integer id) {
		Date date = new Date();		
    	String idTraza = (int)(Math.random()*30+1) + date.getTime() + "_LOG ";	
    	Weather out = new Weather();
		
		//LOGICA DEL SERVICIO
		try {	
			try {
				out = service.getClima(id);
			}catch (Exception e) {
				LOG.error(idTraza +" ERROR MSG: " + e.getLocalizedMessage());
				throw new HttpClientErrorException(HttpStatus.SERVICE_UNAVAILABLE);
			}
			return new ResponseEntity<Weather>(out,out!=null?HttpStatus.OK:HttpStatus.NOT_FOUND);
		}
		catch(HttpStatusCodeException exception) {
			LOG.error(idTraza +" ERROR MSG: " + exception.getLocalizedMessage());
			HttpHeaders headers=new HttpHeaders();
			headers.add("error", exception.getLocalizedMessage());
			headers.add("errorDesc", exception.getResponseBodyAsString());
			return new ResponseEntity<Weather>(out,headers,exception.getStatusCode());
		}
	}
	
	@DeleteMapping("/weather/{id}")
	public ResponseEntity<Weather> deleteClima(@PathVariable("id") Integer id) {
		Date date = new Date();		
    	String idTraza = (int)(Math.random()*30+1) + date.getTime() + "_LOG ";	
    	int out;
		
		//LOGICA DEL SERVICIO
		try {	
			try {
				out = service.deleteClima(id);
			}catch (Exception e) {
				LOG.error(idTraza +" ERROR MSG: " + e.getLocalizedMessage());
				throw new HttpClientErrorException(HttpStatus.SERVICE_UNAVAILABLE);
			}
			return new ResponseEntity<Weather>(new Weather(),out==1?HttpStatus.NO_CONTENT:HttpStatus.NOT_FOUND);
		}
		catch(HttpStatusCodeException exception) {
			LOG.error(idTraza +" ERROR MSG: " + exception.getLocalizedMessage());
			HttpHeaders headers=new HttpHeaders();
			headers.add("error", exception.getLocalizedMessage());
			headers.add("errorDesc", exception.getResponseBodyAsString());
			return new ResponseEntity<Weather>(new Weather(),headers,exception.getStatusCode());
		}
	}
}