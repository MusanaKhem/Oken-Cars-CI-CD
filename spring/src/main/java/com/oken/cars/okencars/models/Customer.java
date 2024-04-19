package com.oken.cars.okencars.models;


import java.util.List;

import jakarta.persistence.*;
import lombok.Data;


//Class Customer
@Data
@Entity
@Table(name = "Customer")
public class Customer extends User {

	@OneToMany(fetch = FetchType.EAGER,targetEntity = Location.class,cascade = CascadeType.ALL)
    private List<Location> locations;
	
	public Customer() {

		super();

	}

	public Customer(String firstname, String lastname, String phone, String email, String address, String password) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.password = password;
		
	}
	

}
