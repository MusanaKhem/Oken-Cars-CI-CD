package com.oken.cars.okencars.models;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "LOCATION")
public class Location {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	protected int id;
    @Column
    protected Date createdate;
    @Column
    protected Date findate; 
    @Column
    protected double price;

	@OneToOne(targetEntity = Customer.class,cascade = CascadeType.ALL)
	@JoinColumn(name = "location_id",foreignKey = @ForeignKey(name = "fk_location_id"))
	protected Customer customer;
	
    // Getters and setters
	public Location() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public Date getFindate() {
		return findate;
	}

	public void setFindate(Date findate) {
		this.findate = findate;
	}
	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Location(Date createdate, Date findate,double price) {
		this.createdate = createdate;
		this.findate = findate;
		this.price = price;
	
	}



	
	

}
