package com.oken.cars.okencars.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.aMapWithSize;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.nio.file.Files;

import com.oken.cars.okencars.models.Car;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;

import com.oken.cars.okencars.repositories.CarRepository;
import com.oken.cars.okencars.repositories.ManagerRepository;

import configuration.ConfigIntegrationTests;


@SqlGroup({
        @Sql(value = "classpath:empty/reset.sql", executionPhase = BEFORE_TEST_METHOD),
        @Sql(value = "classpath:init/car-data.sql", executionPhase = BEFORE_TEST_METHOD)
})
public class CarControllerIntegrationTest extends ConfigIntegrationTests {
	
	@Autowired
	CarRepository mockMvcCarRepository;  

    @Autowired
    private MockMvc mockMvc;
    
    @Test
    void should_create_one_car() throws Exception {
        final File jsonFile = new ClassPathResource("init/car.json").getFile();
        final String carToCreate = Files.readString(jsonFile.toPath());

        this.mockMvc.perform(post("/cars/addCar")
                        .contentType(APPLICATION_JSON)
                        .content(carToCreate))
                .andDo(print())
                .andExpect(status().isOk())//le code de statut attendu à 200 (OK)
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$", aMapWithSize(6)));

        assertThat(this.mockMvcCarRepository.findAll()).hasSize(6);
    }

    @Test
    void should_retrieve_all_cars_with_owners() throws Exception {
        // Effectuez une requête GET pour récupérer toutes les voitures avec leurs propriétaires
        this.mockMvc.perform(get("/cars"))
                .andExpect(status().isOk()) // Le code de statut attendu est 200 (OK)
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(5))); // Vérifie que le résultat contient 6 éléments, par exemple

        // Vous pouvez également ajouter d'autres assertions pour vérifier le contenu des voitures et de leurs propriétaires.
        // Par exemple, vérifier le contenu des voitures et de leurs propriétaires retournés.

        // Assurez-vous que le nombre de voitures retournées correspond à celui attendu.
        assertThat(this.mockMvcCarRepository.findAll()).hasSize(5);

        // Parcourez toutes les voitures pour vérifier la présence ou l'absence de propriétaires.
        for (Car car : this.mockMvcCarRepository.findAll()) {
            if (car.getOwner() != null) {
                // Si la voiture a un propriétaire, effectuez des assertions sur le propriétaire, si nécessaire.
                assertThat(car.getOwner().getLastname()).isNotNull(); // Par exemple, vérifiez le nom du propriétaire.
                assertThat(car.getOwner().getFirstname()).isNotNull();
            }
        }
    }

    @Test
    void should_retrieve_one_car_with_its_owner() throws Exception {
        // Effectuez une requête
        this.mockMvc.perform(get("/cars/{id}", 3))
                .andExpect(status().isOk()) // Le code de statut attendu est 200 (OK)
                .andExpect(content().contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.brand").value("FORD"))
                .andExpect(jsonPath("$.mileage").value(75000))
                .andExpect(jsonPath("$.model").value("Fiesta"))
                .andExpect(jsonPath("$.color").value("SILVER"));
    }



     @Test
    void should_update_existing_car() throws Exception {
        // Chargez les données JSON du fichier pour la mise à jour d'un véhicule
        final File jsonFile = new ClassPathResource("init/car.json").getFile();
        final String carToUpdate = Files.readString(jsonFile.toPath());

        // Effectuez la requête HTTP PUT vers l'endpoint "/user/update/{id}" avec l'identifiant de l'utilisateur à mettre à jour
        this.mockMvc.perform(put("/cars/update/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(carToUpdate))
                .andDo(print())
                .andExpect(status().isOk());

        // Vérifiez que les infos du véhicules ont bien été mis à jour dans le repository
        assertThat(this.mockMvcCarRepository.findAll()).hasSize(5);
    }

    @Test
    void should_delete_one_car() throws Exception {
        this.mockMvc.perform(delete("/cars/delete/{id}", 2))
                .andDo(print())
                .andExpect(status().isOk());

        assertThat(this.mockMvcCarRepository.findAll()).hasSize(4);
    }

}
