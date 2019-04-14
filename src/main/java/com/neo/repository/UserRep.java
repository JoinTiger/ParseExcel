package com.neo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neo.bean.User;

public interface UserRep extends JpaRepository<User, Long> {

}
