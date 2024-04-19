package com.oken.cars.okencars.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oken.cars.okencars.models.Car;


@Repository
public interface CarRepository extends JpaRepository<Car, Integer>{
	Car findById(int id);
}
