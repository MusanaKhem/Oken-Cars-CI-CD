package com.oken.cars.okencars.controllers;

import java.util.List;

import com.oken.cars.okencars.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.oken.cars.okencars.models.Car;
import com.oken.cars.okencars.models.Manager;
import com.oken.cars.okencars.services.CarService;
import com.oken.cars.okencars.services.ManagerService;

@CrossOrigin(origins="*")
@RestController
public class CarController {
	
	@Autowired
    private CarService carService;

    @Autowired
    private OwnerService ownerService;
	
	//Ajout d'une nvelle voiture
    @PostMapping("/cars/addCar")
    public Car addCar(@RequestBody Car car) {
    	return carService.newCar(car);
    }
	
    //Affichage des voitures
    @GetMapping("/cars")
    public List<Car> findAllCars() {
        return carService.getCars();
    }  
    
    //afficher les infos sur l'une des voitures en fonction de l'id
    @GetMapping("/cars/{id}")
    public Car selectOneCar (@PathVariable("id") int id) {
        return carService.getCar(id);
    }  
    
    // modifier les infos d'un véhicule , en fonction de son id
    @PutMapping("/cars/update/{id}")
    public Car update(@PathVariable("id") int id,@RequestBody Car car) {
    	return carService.updateCarInfo(id, car);
    }
    
    //Supprimer un des véhicules via son id
    @DeleteMapping("/cars/delete/{id}")
    public void delCar(@PathVariable("id") int id) {
    	carService.delCar(id);
    }

    //Affichage des voitures d'un propriétaire
    @GetMapping("/cars/owner/{ownerId}")
    public List<Car> getCarsByOwnerId(@PathVariable("ownerId") int ownerId) {
        return ownerService.getCarsByOwnerId(ownerId);
    }


}
