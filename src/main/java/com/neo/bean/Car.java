package com.neo.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="car")
public class Car {
    @Id
    @GeneratedValue
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private double price;
    
    @Column(nullable = false)
    private Long pid;

	public Car() {
		super();
	}

	public Car(Long id, String name, double price, Long pid) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.pid = pid;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}
    
    
    
    
    
}
