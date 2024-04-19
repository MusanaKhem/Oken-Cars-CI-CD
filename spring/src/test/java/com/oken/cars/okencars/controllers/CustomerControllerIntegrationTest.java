
package com.oken.cars.okencars.controllers;

import static org.assertj.core.api.Assertions.assertThat;
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

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;

import com.oken.cars.okencars.repositories.CustomerRepository;

import configuration.ConfigIntegrationTests;


@SqlGroup({
	@Sql(value = "classpath:empty/reset.sql", executionPhase = BEFORE_TEST_METHOD),
	@Sql(value = "classpath:init/customer-data.sql", executionPhase = BEFORE_TEST_METHOD)
})
public class CustomerControllerIntegrationTest extends ConfigIntegrationTests{

	@Autowired 
	private CustomerRepository repository;

	@Autowired 
	private MockMvc mockMvc;

	//TEST INTEGRATION FOR Addcustomer

	@Test void should_add_one_customer() throws Exception {
		final File jsonFile = new ClassPathResource("init/customer.json").getFile();
		final String customerToAdd = Files.readString(jsonFile.toPath());

		this.mockMvc.perform(post("/customers/addCustomer")
				.contentType(APPLICATION_JSON)
				.content(customerToAdd)) .andDo(print());
		assertThat(this.repository.findAll()).hasSize(7);
	}

	//TEST INTEGRATION FOR findAllcustomers

	@Test void should_retrieve_all_customers() throws Exception {
		this.mockMvc.perform(get("/customers"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().contentType(APPLICATION_JSON))
		.andExpect(jsonPath("$").isArray())
		.andExpect(jsonPath("$", hasSize(6)))
		.andExpect(jsonPath("$.[0].id").value(1))
		.andExpect(jsonPath("$.[1].id").value(2))
		.andExpect(jsonPath("$.[2].id").value(3))
		.andExpect(jsonPath("$.[3].id").value(4))
		.andExpect(jsonPath("$.[4].id").value(5));

	}

	//TEST INTEGRATION FOR findcustomerById

	@Test void should_retrieve_one_customer() throws Exception {
		this.mockMvc.perform(get("/customers/edit/{id}", 3))
		.andExpect(jsonPath("$.id").value(3))
		.andExpect(jsonPath("$.firstname").value("Wilem"))
		.andExpect(jsonPath("$.lastname").value("ENGADIA"))
		.andExpect(jsonPath("$.phone").value("0611787540"))
		.andExpect(jsonPath("$.email").value("wilemengadia@gmail.com"))
		.andExpect(jsonPath("$.address").value("Gabon"))
		.andExpect(jsonPath("$.password").value("12345"));
	}


	//TEST INTEGRATION FOR updatecustomer

	@Test void should_update_one_customer() throws Exception {
		final File jsonFile	= new ClassPathResource("init/customer.json").getFile();
		final String customerToUpdate = Files.readString(jsonFile.toPath());
		this.mockMvc.perform(put("/customers/update/{id}",1) 
				.contentType(APPLICATION_JSON)
				.content(customerToUpdate)) .andDo(print());
		assertThat(this.repository.findAll()).hasSize(6);
	}

	//TEST INTEGRATION FOR deletecustomer

	@Test void should_delete_one_customer() throws Exception {
		this.mockMvc.perform(delete("/customers/delete/{id}", 2))
		.andDo(print())
		.andExpect(status().isOk());
		assertThat(this.repository.findAll()).hasSize(5); 
	} 
}


