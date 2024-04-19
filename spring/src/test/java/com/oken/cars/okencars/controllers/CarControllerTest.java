package com.oken.cars.okencars.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
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
import com.oken.cars.okencars.models.Car;
import com.oken.cars.okencars.models.Driver;
import com.oken.cars.okencars.models.Manager;
import com.oken.cars.okencars.services.CarService;

@SpringBootTest(classes = {OkenCarsApplication.class, CarController.class})
public class CarControllerTest {
	
	@Mock
	private Car mockCar;

	@Mock
	private CarService mockCarService;

	@InjectMocks
	private CarController carController = new CarController();
	
	
	 
		@Test
		void contextLoads() {
		}
	
		
		@Test
		void addNewCar(){
			// 1. Define return values the mocks
			Car carExpected = Mockito.mock(Car.class);
			Mockito.when(mockCarService.newCar(mockCar)).thenReturn(carExpected);
			
			// 2. Call the method to test
			Car carActual = carController.addCar(mockCar);
	
			// 3. Check the results
			Assert.assertNotNull(carActual);
	
			assertEquals(carActual,carExpected);	
	
		}
	
	  //Tester affichage de toutes les voitures
	  @Test
		void findAllCars(){
			// 1. Define return values the mocks
			//Manager driverExpected = Mockito.mock(Manager.class);
			List<Car> carList = new ArrayList<>();
			Mockito.when(mockCarService.getCars()).thenReturn(carList);
			// 2. Call the method to test
			List<Car> list = carController.findAllCars();
			// 3. Check the results
			 assertNotNull(list);
			 assertEquals(list,carList);	
		}
	  
	  //Tester affichage d'une seule voiture
	  @Test
		void findOneCar(){
		  	int carId = (int) 1L;
			// 1. Define return values the mocks
			Car car = new Car();
			Mockito.when(mockCarService.getCar(carId)).thenReturn(car);
			// 2. Call the method to test
			Car thecar =carController.selectOneCar(carId);
			// 3. Check the results
			 assertNotNull(thecar);
			 assertEquals(thecar,car);	
		}
	  
	  //Tester mise à jour d'un des managers
	  @Test
	   void testUpdateCar() throws Exception {
		  
		  	//Id du manager à mettre à jour
		  	int carId = 20;
		  	
		  	//Nouvelle infos du manager
		  	Car carUpdate = new Car("RENAUT", "Camry", 100000,"RED");
		  	
		  	//Utilisation de la classe service
		  	Mockito.when(mockCarService.updateCarInfo(carId,carUpdate)).thenReturn(carUpdate);
		  	
		  	//Méthode du controller à implémenter
		  	Car result = carController.update(carId,carUpdate);
		  	
		  	assertEquals(carUpdate,result);
		  
		  	
	    }
	  
	  @Test
	  void testDeleteCar() {
		  
		  int carId = 5;
		  
		  // Simuler le comportement du service pour la suppression d'un gestionnaire par son ID
		  doNothing().when(mockCarService).delCar(carId);
		  
		  // Appeler l'endpoint DELETE pour supprimer le gestionnaire
		  carController.delCar(carId);
		  
		// Vérifier que la méthode delManager du service a été appelée avec le bon ID
	        verify(mockCarService, times(1)).delCar(carId);
	        
	  }
	  
	
	
	
}
