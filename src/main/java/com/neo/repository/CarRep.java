package com.neo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neo.bean.Car;

public interface CarRep extends JpaRepository<Car, Long> {

}
