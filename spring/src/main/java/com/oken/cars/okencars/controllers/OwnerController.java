package com.oken.cars.okencars.controllers;

import java.util.List;

import com.oken.cars.okencars.models.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.oken.cars.okencars.models.Manager;
import com.oken.cars.okencars.models.Owner;
import com.oken.cars.okencars.services.ManagerService;
import com.oken.cars.okencars.services.OwnerService;

@CrossOrigin(origins="*")
@RestController
public class OwnerController {
	
	@Autowired
    private OwnerService ownerService;
	
	//Ajout d'un nouveau owner
    @PostMapping("/owners/addOwner")
    public Owner addOwner(@RequestBody Owner owner) {
    	return ownerService.newOwner(owner);
    }

    //Affichage des managers
    @GetMapping("/owners")
    public List<Owner> findAllOwners() {
        return ownerService.getOwners();
    }  
    
    //afficher les infos sur l'un des managers en fonction de son id
    @GetMapping("/owners/edit/{id}")
    public Owner selectOneOwner (@PathVariable("id") int id) {
        return ownerService.getOwner(id);
    } 
    
    // modifier les infos d'un manager , en fonction de son id
    @PutMapping("/owners/update/{id}")
    public Owner update(@PathVariable("id") int id,@RequestBody Owner owner) {
    	return ownerService.updateOwner(id, owner);
    }
    
    //Supprimer un des managers via son id
    @DeleteMapping("/owners/delete/{id}")
    public void delOwner(@PathVariable("id") int id) {
    	ownerService.delOwner(id);
    	//System.out.println(" Manager supprimée avec succès !!!\n");
    }

    // propriétaire qui ajoute sa voiture dans l'application
    @PostMapping("/owners/addCar/{ownerId}")
    public Owner addCarToOwner(@PathVariable("ownerId") int ownerId, @RequestBody Car car) {
        return ownerService.addCarToOwner(ownerId, car);
    }

    //Affichage des voitures d'un propriétaire spécifique
    @GetMapping("/owners/all-cars/{ownerId}")
    public List<Car> getCarsByOwnerId(@PathVariable("ownerId") int ownerId) {
        return ownerService.getCarsByOwnerId(ownerId);
    }



}
