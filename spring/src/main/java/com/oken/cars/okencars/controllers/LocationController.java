package com.oken.cars.okencars.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.oken.cars.okencars.models.Location;
import com.oken.cars.okencars.services.LocationService;

//Annotation
@CrossOrigin(origins = "*")
@RestController
@Controller
public class LocationController {
	
	@Autowired
	private LocationService locationService;
	
	//Add Location
	@PostMapping("/locations/addLocation")
	public Location addLocation(@RequestBody Location location) {
		// 1. Validate input

		// 2. Processing

		// 3. Return value
		return locationService.saveLocation(location);

	}

	//Get Locations
	@GetMapping("/locations")
	public List<Location> findAllLocations() {
		return locationService.getLocations();

	}
	//Get Location by identifiant
	@GetMapping("/locations/edit/{id}")
	public Location findLocationById(@PathVariable int id) {
		return locationService.getLocationById(id);

	}

	//Update Location by identifiant
	@PutMapping("/locations/update/{id}")
	public Location updateLocation(@PathVariable int id, @RequestBody Location location) {

		return locationService.putLocation(id,location);

	}

	//Delete Location
	@DeleteMapping("/locations/delete/{id}")
	public String deleteLocation(@PathVariable int id) {
		return locationService.removeLocation(id);

	}

}
