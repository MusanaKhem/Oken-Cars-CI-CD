package com.oken.cars.okencars.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name="owners")
public class Owner extends User{

	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
	private List<Car> cars;

	public Owner() {
		super();
	}
	
	public Owner(String firstname, String lastname, String phone, String email, String address, String password) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.password = password;
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}
}
