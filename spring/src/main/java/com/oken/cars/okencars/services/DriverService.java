package com.oken.cars.okencars.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oken.cars.okencars.models.Driver;
import com.oken.cars.okencars.repositories.DriverRepository;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.crypto.password.PasswordEncoder;
//Annotation
@AllArgsConstructor
@Service
@RequiredArgsConstructor
public class DriverService {

	@Autowired
	private DriverRepository repository;

	@Autowired
	private PasswordEncoder encoder;

	public Driver saveDriver(Driver driver) {
		driver.setPassword(encoder.encode(driver.getPassword()));
		driver.setRole("DRIVER");
		return repository.save(driver);
	}
	
	//Get List of Drivers
	public List<Driver> getDrivers() {
		return repository.findAll();

	}
	//Find by identifiant
	public Driver getDriverById(int id) {
		return repository.findById(id);

	}

	//Put by driver
	public Driver putDriver(int id, Driver driver) {
		Driver existingDriver = repository.findById(id);				
		existingDriver.setFirstname(driver.getFirstname());								
		existingDriver.setLastname(driver.getLastname());
		existingDriver.setPhone(driver.getPhone());
		existingDriver.setEmail(driver.getEmail());
		existingDriver.setAddress(driver.getAddress());
		existingDriver.setPassword(encoder.encode(driver.getPassword()));
		existingDriver.setPermit(driver.getPermit());
		return repository.save(existingDriver);


	}
	//delete the Driver
	public String removeDriver(int id){
		repository.deleteById(id);
		return "driver removed !!"+id;

	}
}
