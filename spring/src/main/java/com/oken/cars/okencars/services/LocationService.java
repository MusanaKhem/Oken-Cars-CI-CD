package com.oken.cars.okencars.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oken.cars.okencars.models.Location;
import com.oken.cars.okencars.repositories.LocationRepository;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

//Annotation
@AllArgsConstructor
@Service
@RequiredArgsConstructor
public class LocationService {
	@Autowired
	private LocationRepository repository;


	public Location saveLocation(Location location) {
		
		return repository.save(location);
	}
	
	//Get List of Locations
	public List<Location> getLocations() {
		return repository.findAll();

	}
	//Find by identifiant
	public Location getLocationById(int id) {
		return repository.findById(id).orElse(null);

	}

	//Put by Location
	public Location putLocation(int id, Location location) {
		Location existingLocation = repository.findById(id).orElse(null);				
		existingLocation.setCreatedate(location.getCreatedate());	
		existingLocation.setFindate(location.getFindate());
		existingLocation.setPrice(location.getPrice());
		return repository.save(existingLocation);


	}
	//delete the Location
	public String removeLocation(int id){
		repository.deleteById(id);
		return "location removed !!"+id;

	}

}
