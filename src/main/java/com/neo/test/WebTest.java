package com.neo.test;

import java.text.DateFormat;
import java.util.Date;

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
		
	
		
		User user1 = new User();
		User user2 = new User();
		User user3 = new User();
		
		user1.setUsername("11aa");
		user1.setPassword("daf34");
		
		user2.setUsername("22bb");
		user2.setPassword("daf33");
		
		user3.setUsername("3cc");
		user3.setPassword("daf5fd");
		
		
		userService.save(user1);
		userService.save(user2);
		userService.save(user3);
	}

}
