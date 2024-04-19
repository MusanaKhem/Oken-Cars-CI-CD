package com.oken.cars.okencars.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.oken.cars.okencars.models.Manager;
import com.oken.cars.okencars.repositories.ManagerRepository;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class ManagerService {
	
	@Autowired
	public ManagerRepository managerRepository;
	
	@Autowired
	PasswordEncoder encoder;
	
	//Ajout
			public Manager newManager(Manager manager) {
				//Hachage du mot de passe avant enregistrement en bdd
				manager.setPassword(encoder.encode(manager.getPassword()));
				manager.setRole("MANAGER");
				return this.managerRepository.save(manager);
			}
			
			//Suppression
			public void delManager(int id) {
				 this.managerRepository.deleteById(id);
			}
			//Modification
			public Manager updateManager(int id,Manager manager) {
				Manager modifiedManager = managerRepository.findById(id);
				
				//Mise à jour des datas
				modifiedManager.setLastname(manager.getLastname());
				modifiedManager.setFirstname(manager.getFirstname());
				modifiedManager.setEmail(manager.getEmail());
				modifiedManager.setPhone(manager.getPhone());
				modifiedManager.setAddress(manager.getAddress());
				modifiedManager.setPassword(encoder.encode(manager.getPassword()));
				
				
				//Mise à jour des informations du manager
				return managerRepository.save(modifiedManager);
			}
			
			//Affichage liste 
			public List<Manager> getManagers(){
				return this.managerRepository.findAll();	
			}
			
			//Affichage un manager 
			public Manager getManager(int id){
				return this.managerRepository.findById(id);
			}




}
