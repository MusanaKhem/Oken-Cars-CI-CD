package com.oken.cars.okencars.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.wildfly.common.Assert;

import com.oken.cars.okencars.models.Manager;
import com.oken.cars.okencars.repositories.ManagerRepository;
import com.oken.cars.okencars.services.ManagerService;

@SpringBootTest
public class ManagerControllerTest {
	
	  @Mock
	  private Manager mockManager;
	  
	  @Mock
	  private ManagerService mockManagerService;

	  @InjectMocks
	  private ManagerController managerController =  new ManagerController();
	  
	  @Mock
	  ManagerRepository mockManagerRepository;  
	  
	  @Test
	  void contextLoads() {
	  }

	  //Tester ajout de manager
	  @Test
	  void addManager(){

		  // 1. Define return values the mocks
		Manager managerExpected = Mockito.mock(Manager.class);
			Mockito.when(mockManagerService.newManager(mockManager)).thenReturn(managerExpected);
			
		
			// 2. Call the method to test
			Manager managerActual = managerController.addManager(mockManager);
			
			// 3. Check the results
			Assert.assertNotNull(managerActual);
			assertEquals(managerActual,managerExpected);	
		
	  }
	  
	  //Tester affichage de tous les managers
	  @Test
		void findAllManagers(){
			// 1. Define return values the mocks
			//Manager driverExpected = Mockito.mock(Manager.class);
			List<Manager> managerList = new ArrayList<>();
			Mockito.when(mockManagerService.getManagers()).thenReturn(managerList);
			// 2. Call the method to test
			List<Manager> list = managerController.findAllManagers();
			// 3. Check the results
			 assertNotNull(list);
			 assertEquals(list,managerList);	
		}
	  
	  //Tester affichage d'un seul tous les managers
	  @Test
		void findOneManager(){
		  	int managerId = (int) 1L;
			// 1. Define return values the mocks
			Manager manager = new Manager();
			Mockito.when(mockManagerService.getManager(managerId)).thenReturn(manager);
			// 2. Call the method to test
			Manager themanager = managerController.selectOneManager(managerId);
			// 3. Check the results
			 assertNotNull(themanager);
			 assertEquals(themanager,manager);	
		}

	  
	  //Tester mise à jour d'un des managers
	  @Test
	   void testUpdateManager() throws Exception {
		  
		  	//Id du manager à mettre à jour
		  	int managerId = 20;
		  	
		  	//Nouvelle infos du manager
		  	Manager managerUpdate = new Manager("Loubamono", "Elisabeth", "elisabeth@hotmail.fr","0914372911", "10 Rue Dragon", "12345abc");
		  	
		  	//Utilisation de la classe service
		  	Mockito.when(mockManagerService.updateManager(managerId,managerUpdate)).thenReturn(managerUpdate);
		  	
		  	//Méthode du controller à implémenter
		  	Manager result = managerController.update(managerId,managerUpdate);
		  	
		  	assertEquals(managerUpdate,result);
		  
		  	
	    }
	  
	  @Test
	  void testDeleteManager() {
		  
		  int managerId = 5;
		  
		  // Simuler le comportement du service pour la suppression d'un gestionnaire par son ID
		  doNothing().when(mockManagerService).delManager(managerId);
		  
		  // Appeler l'endpoint DELETE pour supprimer le gestionnaire
		  managerController.delManager(managerId);
		  
		// Vérifier que la méthode delManager du service a été appelée avec le bon ID
	        verify(mockManagerService, times(1)).delManager(managerId);
	        
	        
	        
	  }
	  
	  

}
