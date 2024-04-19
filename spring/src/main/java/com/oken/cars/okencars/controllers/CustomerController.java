package com.oken.cars.okencars.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.oken.cars.okencars.models.Customer;
import com.oken.cars.okencars.services.CustomerService;




//Annotation
@CrossOrigin(origins = "*")
@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	//Add Customer
	@PostMapping("/customers/addCustomer")
	public Customer addCustomer(@RequestBody Customer customer) {
		// 1. Validate input

		// 2. Processing

		// 3. Return value
		return customerService.saveCustomer(customer);

	}

	//Get Customers
	@GetMapping("/customers")
	public List<Customer> findAllCustomers() {
		return customerService.getCustomers();

	}
	//Get Customer by identifiant
	@GetMapping("/customers/edit/{id}")
	public Customer findCustomerById(@PathVariable int id) {
		return customerService.getCustomerById(id);

	}

	//Update customer by identifiant
	@PutMapping("/customers/update/{id}")
	public Customer updateCustomer(@PathVariable int id, @RequestBody Customer customer) {

		return customerService.putCustomer(id,customer);

	}

	//Delete customer
	@DeleteMapping("/customers/delete/{id}")
	public String deleteCustomer(@PathVariable int id) {
		return customerService.removeCustomer(id);

	}

}
