package com.neo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.neo.bean.ExcelResult;
import com.neo.bean.Person;

public interface PersonRep extends JpaRepository<Person, Long> {
	
	@Query("select p.id as Person.id, p.name as Person.name,"
	+ "p.sex as Person.sex, p.age as Person.age," 
			+ "c.id as Car.id, c.name as Car.name, c.price as Car.price," + 
	" c.pid as Car.pid" + "from Person p join Car c on(p.id = c.pid)")
	List<ExcelResult> getPersonAndCars();
}
