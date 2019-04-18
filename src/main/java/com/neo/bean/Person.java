package com.neo.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity(name="person")
public class Person {
    @Id
    private String id;
    
    private String name;
    
    private char sex;
    
    @Column(nullable = false)
    private int age;
    
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="pid")
	private Set<Car> cars = new HashSet<Car>();
    
    
	public Person() {
		super();
	}

	public Person(String id, String name, char sex, int age, Set<Car> cars) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.cars = cars;
	}

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public char getSex() {
		return sex;
	}


	public void setSex(char sex) {
		this.sex = sex;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public Set<Car> getCars() {
		return cars;
	}

	public void setCars(Set<Car> cars) {
		this.cars = cars;
	}

	
	
}
