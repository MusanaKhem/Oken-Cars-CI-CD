package com.oken.cars.okencars.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oken.cars.okencars.models.Car;
import com.oken.cars.okencars.repositories.CarRepository;

@Service
public class CarService {
	
	@Autowired
	public CarRepository carRepository;
	
	
	 public Car newCar(Car car) {
		return this.carRepository.save(car);
	 } 
	 
	//Suppression
	public void delCar(int id) {
		this.carRepository.deleteById(id);
	}
	
	
	//Modification
	public Car updateCarInfo(int carId, Car car) {
		Car modifiedCar = carRepository.findById(carId);
		if (modifiedCar != null) {
			modifiedCar.setBrand(car.getBrand());
			modifiedCar.setModel(car.getModel());
			modifiedCar.setMileage(car.getMileage());
			modifiedCar.setColor(car.getColor());
			return carRepository.save(modifiedCar);
		}
		return null; // Gérer le cas où la voiture n'est pas trouvée
	}


	//Affichage liste 
	public List<Car> getCars(){
		return this.carRepository.findAll();	
	}
	
	//Affichage un manager 
	public Car getCar(int id){
		return this.carRepository.findById(id);
	}
	
	
	 

}
