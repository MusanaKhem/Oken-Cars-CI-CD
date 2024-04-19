package com.oken.cars.okencars.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Entity
@Table(name = "cars")
public class Car {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	
	@Column
	private String brand;
	
	@Column
	private String model;
	
	@Column
	private int mileage;
	
	@Column
	private String color;

	@ManyToOne
	@JoinColumn(name = "owner_id", foreignKey = @ForeignKey(name = "fk_cars"))
	@OnDelete(action = OnDeleteAction.CASCADE) // Spécification de l'action CASCADE pour supprimer les voitures associées lorsque le propriétaire est supprimé.
	private Owner owner;
	

	public Car() {
		super();
	}

	public Car(String brand, String model, int mileage, String color) {
		this.brand = brand;
		this.model = model;
		this.mileage = mileage;
		this.color = color;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getMileage() {
		return mileage;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}
}
