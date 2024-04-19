package com.oken.cars.okencars.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.wildfly.common.Assert;

import com.oken.cars.okencars.OkenCarsApplication;
import com.oken.cars.okencars.models.Driver;
import com.oken.cars.okencars.services.DriverService;

@SpringBootTest(classes = {OkenCarsApplication.class, DriverController.class})
public class DriverControllerTest {


	@Mock
	private Driver mockDriver;

	@Mock
	private DriverService mockDriverService;

	@InjectMocks
	private DriverController driverController = new DriverController();

	@Test
	void contextLoads() {
	}
	//TEST UNITAIRE FOR METHOD AddDrivers
	@Test
	void addDriver(){
		// 1. Define return values the mocks
		Driver driverExpected = Mockito.mock(Driver.class);
		Mockito.when(mockDriverService.saveDriver(mockDriver)).thenReturn(driverExpected);
		// 2. Call the method to test

		Driver driverActual = driverController.addDriver(mockDriver);

		// 3. Check the results
		Assert.assertNotNull(driverActual);

		assertEquals(driverActual,driverExpected);	

	}

	//TEST UNITAIRE FOR METHOD findAllDrivers
	@Test
	void findAllDrivers(){

		// 1. Define return values the mocks

		List<Driver> driversList = new ArrayList<>();

		Mockito.when(mockDriverService.getDrivers()).thenReturn(driversList);

		// 2. Call the method to test

		List<Driver> list = driverController.findAllDrivers();

		// 3. Check the results

		assertNotNull(list);

		assertEquals(list,driversList);	

	}
	//TEST UNITAIRE FOR METHOD findDriverById

	@Test
	public void findDriverById(){

		int driverId =  2;

		// 1. Define return values the mocks
		Driver driver = Mockito.mock(Driver.class);

		Mockito.when(mockDriverService.getDriverById(driverId)).thenReturn(driver);

		// 2. Call the method to test

		Driver result = driverController.findDriverById(driverId);

		// 3. Check the results

		assertNotNull(result);
		assertEquals(result,driver);    
		assertThat(result).isEqualTo(driver);

	}

	//TEST UNITAIRE FOR METHOD updateDriver

	@Test
	public void updateDriver() {

		// 1. Define add values the mocks

		int driverId = 7;

		Driver driverUpdate = new Driver("LoicII", "NKWEMII", "0768203254", "loic2@gmail", "Cameroun", "11111", "Permis C");  

		Mockito.when(mockDriverService.putDriver(driverId,driverUpdate)).thenReturn(driverUpdate);

		// 2. Call the method to test
		Driver result = driverController.updateDriver(driverId, driverUpdate);

		// 3. Check the results

		assertEquals(driverUpdate, result);


	}
	//TEST UNITAIRE FOR METHOD deleteDriver
	@Test
	public void deleteDriver() {

		int driverId = 1;

		// 1. Define add values the mocks

		Mockito.when(mockDriverService.removeDriver(driverId)).thenReturn("Delete "+driverId);

		// 2. Call the method to test

		driverController.deleteDriver(driverId);     

		// 3. Check the results

		verify(mockDriverService, times(1)).removeDriver(driverId);
		

	}




}



