package com.oken.cars.okencars.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oken.cars.okencars.models.Location;



@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
    Optional<Location> findById(Integer id); 
}
