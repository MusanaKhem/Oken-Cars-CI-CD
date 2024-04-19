package com.oken.cars.okencars.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oken.cars.okencars.models.Driver;
import com.oken.cars.okencars.models.Manager;

//Interface
@Repository
public interface DriverRepository extends JpaRepository<Driver, Integer>{
	
 Driver findById(int id);
	
	
}
