package com.neo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neo.bean.Person;

public interface PersonRep extends JpaRepository<Person, Long> {
	
}
