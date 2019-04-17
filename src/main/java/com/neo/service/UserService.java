package com.neo.service;

import java.util.Date;
import java.text.DateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.neo.bean.User;
import com.neo.repository.UserRep;

@Component
public class UserService {
	
	@Resource
	private UserRep userRep;
	
	@Transactional
	@Modifying
	@Query
	public void save(User user) {
		Date date = new Date();
		
		user.setRegTime(date);
		userRep.save(user);
	}
	
	
	@Transactional
	@Modifying
	@Query
	public void saveAll(List<User> users) {
		Date date = new Date();
		
		for(User user : users) {
			user.setRegTime(date);
			userRep.save(user);
		}
	}
	
	@Transactional
	@Query
	public List<User> getAll() {
		List<User> users = userRep.findAll();
		return users;
	}
}
