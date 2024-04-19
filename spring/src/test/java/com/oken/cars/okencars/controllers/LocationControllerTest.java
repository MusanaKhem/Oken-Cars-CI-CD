package com.oken.cars.okencars.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.wildfly.common.Assert;

import com.oken.cars.okencars.OkenCarsApplication;
import com.oken.cars.okencars.models.Location;
import com.oken.cars.okencars.services.LocationService;

@SpringBootTest(classes = {OkenCarsApplication.class, LocationController.class})
public class LocationControllerTest {


	@Mock
	private Location mockLocation;

	@Mock
	private LocationService mockLocationService;

	@InjectMocks
	private LocationController locationController = new LocationController();

	@Test
	void contextLoads() {
	}
	//TEST UNITAIRE FOR METHOD AddLocations
	@Test
	void addLocation(){
		// 1. Define return values the mocks
		Location locationExpected = Mockito.mock(Location.class);
		
		Mockito.when(mockLocationService.saveLocation(mockLocation)).thenReturn(locationExpected);
		// 2. Call the method to test

		Location locationActual = locationController.addLocation(mockLocation);

		// 3. Check the results
		Assert.assertNotNull(locationActual);

		assertEquals(locationActual,locationExpected);	

	}

	//TEST UNITAIRE FOR METHOD findAllLocations
	@Test
	void findAllLocations(){

		// 1. Define return values the mocks

		List<Location> locationsList = new ArrayList<>();

		Mockito.when(mockLocationService.getLocations()).thenReturn(locationsList);

		// 2. Call the method to test

		List<Location> list = locationController.findAllLocations();

		// 3. Check the results

		assertNotNull(list);

		assertEquals(list,locationsList);	

	}
	//TEST UNITAIRE FOR METHOD findLocationById

	@Test
	public void findLocationById(){

		int locationId =  2;

		// 1. Define return values the mocks
		Location location = Mockito.mock(Location.class);

		Mockito.when(mockLocationService.getLocationById(locationId)).thenReturn(location);

		// 2. Call the method to test

		Location result = locationController.findLocationById(locationId);

		// 3. Check the results

		assertNotNull(result);
		assertEquals(result,location);    
		assertThat(result).isEqualTo(location);

	}

	//TEST UNITAIRE FOR METHOD updateLocation

	@Test
	public void updateLocation() {

		// 1. Define add values the mocks

		int locationId = 1;
		
		Date date1 = new Date(99, 1, 1);
		Date date2 = new Date(23, 1, 1);
        
		Location locationUpdate = new Location(date1,date2,200.60); 

		Mockito.when(mockLocationService.putLocation(locationId,locationUpdate)).thenReturn(locationUpdate);

		// 2. Call the method to test
		Location result = locationController.updateLocation(locationId, locationUpdate);

		// 3. Check the results

		assertEquals(locationUpdate, result);


	}
	
	//TEST UNITAIRE FOR METHOD deleteLocation
	@Test
	public void deleteLocation() {

		int locationId = 1;

		// 1. Define add values the mocks

		Mockito.when(mockLocationService.removeLocation(locationId)).thenReturn("Delete "+locationId);

		// 2. Call the method to test

		locationController.deleteLocation(locationId);     

		// 3. Check the results

		verify(mockLocationService, times(1)).removeLocation(locationId);
		

	}




}



