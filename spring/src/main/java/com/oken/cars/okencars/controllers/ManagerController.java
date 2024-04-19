package com.oken.cars.okencars.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oken.cars.okencars.models.Manager;
import com.oken.cars.okencars.services.ManagerService;

@CrossOrigin(origins="*")
@RestController
public class ManagerController {
	
	@Autowired
    private ManagerService managerService;
	  
	//Ajout d'un nouveau manager
    @PostMapping("/managers/addManager")
    public Manager addManager(@RequestBody Manager manager) {
    	return managerService.newManager(manager);
    }
	
    //Affichage des managers
    @GetMapping("/managers")
    public List<Manager> findAllManagers() {
        return managerService.getManagers();
    }  
    
    //afficher les infos sur l'un des managers en fonction de son id
    @GetMapping("/managers/{id}")
    public Manager selectOneManager (@PathVariable("id") int id) {
        return managerService.getManager(id);
    }  
    
    // modifier les infos d'un manager , en fonction de son id
    @PutMapping("/managers/update/{id}")
    public Manager update(@PathVariable("id") int id,@RequestBody Manager manager) {
    	return managerService.updateManager(id, manager);
    }
    
    //Supprimer un des managers via son id
    @DeleteMapping("/managers/delete/{id}")
    public void delManager(@PathVariable("id") int id) {
    	managerService.delManager(id);
    	//System.out.println(" Manager supprimée avec succès !!!\n");
    }

}
