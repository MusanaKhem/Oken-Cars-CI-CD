package com.oken.cars.okencars.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

import com.oken.cars.okencars.OkenCarsApplication;
import com.oken.cars.okencars.models.Customer;
import com.oken.cars.okencars.services.CustomerService;

@SpringBootTest(classes = {OkenCarsApplication.class, CustomerController.class})
public class CustomerControllerTest {


	@Mock
	private Customer mockCustomer;

	@Mock
	private CustomerService mockCustomerService;

	@InjectMocks
	private CustomerController customerController = new CustomerController();

	@Test
	void contextLoads() {
	}
	//TEST UNITAIRE FOR METHOD AddCustomers
	@Test
	void addCustomer(){
		// 1. Define return values the mocks
		Customer customerExpected = Mockito.mock(Customer.class);
		Mockito.when(mockCustomerService.saveCustomer(mockCustomer)).thenReturn(customerExpected);
		// 2. Call the method to test

		Customer customerActual = customerController.addCustomer(mockCustomer);

		// 3. Check the results
		Assert.assertNotNull(customerActual);

		assertEquals(customerActual,customerExpected);	

	}

	//TEST UNITAIRE FOR METHOD findAllCustomers
	@Test
	void findAllCustomers(){

		// 1. Define return values the mocks

		List<Customer> customersList = new ArrayList<>();

		Mockito.when(mockCustomerService.getCustomers()).thenReturn(customersList);

		// 2. Call the method to test

		List<Customer> list = customerController.findAllCustomers();

		// 3. Check the results

		assertNotNull(list);

		assertEquals(list,customersList);	

	}
	//TEST UNITAIRE FOR METHOD findCustomerById

	@Test
	public void findCustomerById(){

		int customerId =  2;

		// 1. Define return values the mocks
		Customer customer = Mockito.mock(Customer.class);

		Mockito.when(mockCustomerService.getCustomerById(customerId)).thenReturn(customer);

		// 2. Call the method to test

		Customer result = customerController.findCustomerById(customerId);

		// 3. Check the results

		assertNotNull(result);
		assertEquals(result,customer);    
		assertThat(result).isEqualTo(customer);

	}

	//TEST UNITAIRE FOR METHOD updateCustomer

	@Test
	public void updateCustomer() {

		// 1. Define add values the mocks

		int customerId = 7;

		Customer customerUpdate = new Customer("LoicII", "NKWEMII", "0768203254", "loic2@gmail", "Cameroun", "11111");  

		Mockito.when(mockCustomerService.putCustomer(customerId,customerUpdate)).thenReturn(customerUpdate);

		// 2. Call the method to test
		Customer result = customerController.updateCustomer(customerId, customerUpdate);

		// 3. Check the results

		assertEquals(customerUpdate, result);


	}
	//TEST UNITAIRE FOR METHOD deleteCustomer
	@Test
	public void deleteCustomer() {

		int customerId = 1;

		// 1. Define add values the mocks

		Mockito.when(mockCustomerService.removeCustomer(customerId)).thenReturn("Delete "+customerId);

		// 2. Call the method to test

		customerController.deleteCustomer(customerId);     

		// 3. Check the results

		verify(mockCustomerService, times(1)).removeCustomer(customerId);
		

	}




}



