package com.neo.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="person")
public class Person {
    @Id
    @GeneratedValue
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private char sex;
    
    @Column(nullable = false)
    private int age;
    
	public Person() {
		super();
	}

	public Person(Long id, String name, char sex, int age) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.age = age;
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
    
}
