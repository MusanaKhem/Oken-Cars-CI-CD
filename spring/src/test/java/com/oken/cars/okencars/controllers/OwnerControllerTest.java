package com.oken.cars.okencars.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.wildfly.common.Assert;

import com.oken.cars.okencars.models.Manager;
import com.oken.cars.okencars.models.Owner;
import com.oken.cars.okencars.repositories.ManagerRepository;
import com.oken.cars.okencars.repositories.OwnerRepository;
import com.oken.cars.okencars.services.ManagerService;
import com.oken.cars.okencars.services.OwnerService;

@SpringBootTest
public class OwnerControllerTest {
	
	 @Mock
	  private Owner mockOwner;
	  
	  @Mock
	  private OwnerService mockOwnerService;
	  
	  @Mock
	  OwnerRepository mockOwnerRepository;  

	  @InjectMocks
	  private OwnerController ownerController =  new OwnerController();
	  
	  @Test
	  void contextLoads() {
	  }
	  
	  //Tester ajout de owner
	  @Test
	  void addOwner(){

		  // 1. Define return values the mocks
		  Owner ownerExpected = Mockito.mock(Owner.class);
		  Mockito.when(mockOwnerService.newOwner(mockOwner)).thenReturn(ownerExpected);
			
		
		  // 2. Call the method to test
		  Owner ownerActual = ownerController.addOwner(mockOwner);
			
		  // 3. Check the results
		  Assert.assertNotNull(ownerActual);
		  assertEquals(ownerActual,ownerExpected);	
		
	  }
	  
	  //Tester affichage de tous les owners
	  @Test
	  void findAllOwners(){
			// 1. Define return values the mocks
			//Manager driverExpected = Mockito.mock(Manager.class);
			List<Owner> ownerList = new ArrayList<>();
			Mockito.when(mockOwnerService.getOwners()).thenReturn(ownerList);
			// 2. Call the method to test
			List<Owner> list = ownerController.findAllOwners();
			// 3. Check the results
			 assertNotNull(list);
			 assertEquals(list,ownerList);	
	  }
	  
	  //Tester affichage d'un seul tous les owner
	  @Test
		void findOneOwner(){
		  	int ownerId = (int) 1L;
			// 1. Define return values the mocks
			Owner owner = new Owner();
			Mockito.when(mockOwnerService.getOwner(ownerId)).thenReturn(owner);
			// 2. Call the method to test
			Owner theOwner = ownerController.selectOneOwner(ownerId);
			// 3. Check the results
			 assertNotNull(theOwner);
			 assertEquals(theOwner,owner);	
		}
	  
	  
	  //Tester mise à jour d'un des managers
	  @Test
	   void testUpdateOwner() throws Exception {
		  
		  	//Id du manager à mettre à jour
		  	int ownerId = 20;
		  	
		  	//Nouvelle infos du manager
		  	Owner ownerUpdate = new Owner("Loubamono", "Elisabeth", "elisabeth@hotmail.fr","0914372911", "10 Rue Dragon", "12345abc");
		  	
		  	//Utilisation de la classe service
		  	Mockito.when(mockOwnerService.updateOwner(ownerId,ownerUpdate)).thenReturn(ownerUpdate);
		  	
		  	//Méthode du controller à implémenter
		  	Owner result = ownerController.update(ownerId,ownerUpdate);
		  	
		  	assertEquals(ownerUpdate,result);
		  
		  	
	    }
	  
	  
	  @Test
	  void testDeleteOwner() {
		  
		  int ownerId = 5;
		  
		  // Simuler le comportement du service pour la suppression d'un gestionnaire par son ID
		  doNothing().when(mockOwnerService).delOwner(ownerId);
		  
		  // Appeler l'endpoint DELETE pour supprimer le gestionnaire
		  ownerController.delOwner(ownerId);
		  
		// Vérifier que la méthode delManager du service a été appelée avec le bon ID
	        verify(mockOwnerService, times(1)).delOwner(ownerId);
	        
	  }
	  

}
