package com.oken.cars.okencars.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.oken.cars.okencars.models.Owner;

@Repository
public interface OwnerRepository extends JpaRepository <Owner, Integer>{
	Owner findById(int id);
}
