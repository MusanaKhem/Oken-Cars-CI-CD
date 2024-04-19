package com.oken.cars.okencars.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oken.cars.okencars.models.Customer;
import com.oken.cars.okencars.repositories.CustomerRepository;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.crypto.password.PasswordEncoder;
//Annotation
@AllArgsConstructor
@Service
@RequiredArgsConstructor
public class CustomerService {

	@Autowired
	private CustomerRepository repository;

	@Autowired
	private PasswordEncoder encoder;

	public Customer saveCustomer(Customer customer) {
		customer.setPassword(encoder.encode(customer.getPassword()));
		customer.setRole("CUSTOMER");
		return repository.save(customer);
	}
	
	//Get List of Customers
	public List<Customer> getCustomers() {
		return repository.findAll();

	}
	//Find by identifiant
	public Customer getCustomerById(int id) {
		return repository.findById(id);

	}

	//Put by Customer
	public Customer putCustomer(int id, Customer customer) {
		Customer existingCustomer = repository.findById(id);				
		existingCustomer.setFirstname(customer.getFirstname());								
		existingCustomer.setLastname(customer.getLastname());
		existingCustomer.setPhone(customer.getPhone());
		existingCustomer.setEmail(customer.getEmail());
		existingCustomer.setAddress(customer.getAddress());
		existingCustomer.setPassword(encoder.encode(customer.getPassword()));
		return repository.save(existingCustomer);


	}
	//delete the Customer
	public String removeCustomer(int id){
		repository.deleteById(id);
		return "customer removed !!"+id;

	}
	


}
