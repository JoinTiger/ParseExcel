package com.neo.test;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.neo.bean.User;
import com.neo.repository.UserRep;
import com.neo.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebTest {


	
	@Resource
	private UserService userService;
	
	
	
	@Test
	public void contextLoads() {
		
	
		
		
		List<User> all = userService.getAll();
		
		for(User user : all) {
			System.out.print(user.getId() + "            ");
			System.out.print(user.getUsername() + "            ");
			System.out.print(user.getPassword() + "            ");
			System.out.print(user.getRegTime() + "            ");
			System.out.println();
		}
	}

}
