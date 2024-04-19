package com.oken.cars.okencars.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.oken.cars.okencars.models.Driver;
import com.oken.cars.okencars.services.DriverService;




//Annotation
@CrossOrigin(origins = "*")
@RestController
@Controller
public class DriverController {

	@Autowired
	private DriverService driverService;
	
	//Add driver
	@PostMapping("/drivers/addDriver")
	public Driver addDriver(@RequestBody Driver driver) {
		// 1. Validate input

		// 2. Processing

		// 3. Return value
		return driverService.saveDriver(driver);

	}

	//Get drivers
	@GetMapping("/drivers")
	public List<Driver> findAllDrivers() {
		return driverService.getDrivers();

	}
	//Get driver by identifiant
	@GetMapping("/drivers/edit/{id}")
	public Driver findDriverById(@PathVariable int id) {
		return driverService.getDriverById(id);

	}

	//Update driver by identifiant
	@PutMapping("/drivers/update/{id}")
	public Driver updateDriver(@PathVariable int id, @RequestBody Driver driver) {

		return driverService.putDriver(id,driver);

	}

	//Delete Driver
	@DeleteMapping("/drivers/delete/{id}")
	public String deleteDriver(@PathVariable int id) {
		return driverService.removeDriver(id);

	}

}
