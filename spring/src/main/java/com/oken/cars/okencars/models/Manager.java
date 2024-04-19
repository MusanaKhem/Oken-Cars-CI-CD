package com.oken.cars.okencars.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="managers")
public class Manager extends User{

	public Manager() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Manager(String firstname, String lastname, String phone, String email, String address, String password) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.password = password;
	}

}
