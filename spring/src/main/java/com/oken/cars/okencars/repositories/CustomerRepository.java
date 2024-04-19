package com.oken.cars.okencars.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oken.cars.okencars.models.Customer;

;

//Interface
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	 Customer findById(int id);
	

	
}
