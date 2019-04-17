package com.neo.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.neo.bean.Car;
import com.neo.bean.ExcelResult;
import com.neo.bean.Person;
import com.neo.repository.PersonRep;
import com.neo.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebTest {


	
	@Resource
	private UserService userService;
	
	@Resource
	private  PersonRep personRep;
	
	
	@Test
	public void contextLoads() {
		
		List<ExcelResult> personAndCars = personRep.getPersonAndCars();
		
		for(ExcelResult excelResult : personAndCars) {
			Person person = excelResult.getPerson();
			List<Car> car = excelResult.getCar();
			
			System.out.println(person);
			System.out.println(car);
			
//			System.out.print("name:" + person.getName());
//			System.out.print("age:" + person.getAge());
//			System.out.println();
//			System.out.println(car);
			
			
		}
	
		
		
//		List<User> all = userService.getAll();
//		
//		for(User user : all) {
//			System.out.print(user.getId() + "            ");
//			System.out.print(user.getUsername() + "            ");
//			System.out.print(user.getPassword() + "            ");
//			System.out.print(user.getRegTime() + "            ");
//			System.out.println();
//		}
	}

}
