package com.oken.cars.okencars.models;


import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;


//Class driver
@Data
@Entity
@Table(name = "DRIVER")
public class Driver extends User {


	@Column
	protected String permit;	
	
	public String getPermit() {
		return permit; 
	}

	public void setPermit(String permit) {
		this.permit = permit;
	}
	
	
	public Driver() {

		super();

	}

	public Driver(String firstname, String lastname, String phone, String email, String address, String password,String permit) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.password = password;
		this.permit = permit;
	}
	

}
