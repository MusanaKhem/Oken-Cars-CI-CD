package com.oken.cars.okencars.services;

import java.util.List;

import com.oken.cars.okencars.models.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.oken.cars.okencars.models.Owner;
import com.oken.cars.okencars.repositories.OwnerRepository;

@Service
public class OwnerService {
	
	@Autowired
	public OwnerRepository ownerRepository;
	
	@Autowired
	PasswordEncoder encoder;
	
	//Ajout
	public Owner newOwner(Owner owner) {
		//Hachage du mot de passe avant enregistrement en bdd
		owner.setPassword(encoder.encode(owner.getPassword()));
		owner.setRole("OWNER");
		return this.ownerRepository.save(owner);
	}
	
	//Suppression
	public void delOwner(int id) {
		 this.ownerRepository.deleteById(id);
	}
	
	
	//Modification
	public Owner updateOwner(int id,Owner owner) {
		Owner modifiedOwner = ownerRepository.findById(id);
		
		//Mise à jour des datas
		modifiedOwner.setLastname(owner.getLastname());
		modifiedOwner.setFirstname(owner.getFirstname());
		modifiedOwner.setEmail(owner.getEmail());
		modifiedOwner.setPhone(owner.getPhone());
		modifiedOwner.setAddress(owner.getAddress());
		modifiedOwner.setPassword(encoder.encode(owner.getPassword()));
		
		
		//Mise à jour des informations du manager
		return ownerRepository.save(modifiedOwner);
	}
	
	//Affichage liste 
	public List<Owner> getOwners(){
		return this.ownerRepository.findAll();	
	}
	
	//Affichage un manager 
	public Owner getOwner(int id){
		return this.ownerRepository.findById(id);
	}
	
	// récupérer le propriétaire par son ID
	public Owner addCarToOwner(int ownerId, Car car) {
		Owner owner = ownerRepository.findById(ownerId);
		if (owner != null) {
			car.setOwner(owner);
			owner.getCars().add(car);
			return ownerRepository.save(owner);
		}
		return null; // Gérer le cas où le propriétaire n'est pas trouvé
	}

	//liste des voitures d'un propriétaire spécifique
	public List<Car> getCarsByOwnerId(int ownerId) {
		Owner owner = ownerRepository.findById(ownerId);
		if (owner != null) {
			return owner.getCars();
		}
		return null; // Gérer le cas où le propriétaire n'est pas trouvé
	}




}
