package com.oken.cars.okencars.controllers;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.http.MediaType;

import java.io.File;
import java.nio.file.Files;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.aMapWithSize;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.oken.cars.okencars.repositories.ManagerRepository;

import configuration.ConfigIntegrationTests;


@SqlGroup({
        @Sql(value = "classpath:empty/reset.sql", executionPhase = BEFORE_TEST_METHOD),
        @Sql(value = "classpath:init/manager-data.sql", executionPhase = BEFORE_TEST_METHOD)
})
public class ManagerControllerIntegrationTest extends ConfigIntegrationTests{
	
	@Autowired
	ManagerRepository mockMvcManagerRepository;  

    @Autowired
    private MockMvc mockMvc;
    
    //Création d'un manager
    @Test
    void should_create_one_manager() throws Exception {
        final File jsonFile = new ClassPathResource("init/manager.json").getFile();
        final String userToCreate = Files.readString(jsonFile.toPath());

        this.mockMvc.perform(post("/managers/addManager")
                        .contentType(APPLICATION_JSON)
                        .content(userToCreate))
                .andDo(print())
                .andExpect(status().isOk())//le code de statut attendu à 200 (OK)
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$", aMapWithSize(8)));

        assertThat(this.mockMvcManagerRepository.findAll()).hasSize(6);
    }
    
    //Mettre à jour les informations d'un manager
    @Test
    void should_update_existing_manager() throws Exception {
        // Chargez les données JSON du fichier pour la mise à jour de l'utilisateur
        final File jsonFile = new ClassPathResource("init/manager.json").getFile();
        final String managerToUpdate = Files.readString(jsonFile.toPath());

        // Effectuez la requête HTTP PUT vers l'endpoint "/user/update/{id}" avec l'identifiant de l'utilisateur à mettre à jour
        this.mockMvc.perform(put("/managers/update/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(managerToUpdate))
                .andDo(print())
                .andExpect(status().isOk());

        // Vérifiez que l'utilisateur a bien été mis à jour dans le repository
        // Assurez-vous d'avoir 6 utilisateurs dans le repository (ou le nombre attendu après la mise à jour)
        assertThat(this.mockMvcManagerRepository.findAll()).hasSize(5);
    }
	
	
    //Retrouver un seul manager
    @Test
    void should_retrieve_one_manager() throws Exception {
        this.mockMvc.perform(get("/managers/{id}", 5))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(5))
                .andExpect(jsonPath("$.firstname").value("Lee"))
                .andExpect(jsonPath("$.lastname").value("Emma"))
                .andExpect(jsonPath("$.email").value("emma.lee@example.com"))
                .andExpect(jsonPath("$.phone").value("12 45 278 39"))
                .andExpect(jsonPath("$.address").value("567 Pine Lane"))
                .andExpect(jsonPath("$.password").value("mypassword123"));
    }
    
    
      //Retrouver tous les managers
     @Test
    void should_retrieve_all_users() throws Exception {
        this.mockMvc.perform(get("/managers"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$.[0].id").value(1))
                .andExpect(jsonPath("$.[1].id").value(2))
                .andExpect(jsonPath("$.[2].id").value(3))
                .andExpect(jsonPath("$.[3].id").value(4))
                .andExpect(jsonPath("$.[4].id").value(5));
    }
    
    //Supprimer un manager
    @Test
    void should_delete_one_user() throws Exception {
        this.mockMvc.perform(delete("/managers/delete/{id}", 3))
                .andDo(print())
                .andExpect(status().isOk());

        assertThat(this.mockMvcManagerRepository.findAll()).hasSize(4);
    }
    
}
