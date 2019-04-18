package com.neo.test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.neo.bean.Car;
import com.neo.bean.Person;
import com.neo.repository.CarRep;
import com.neo.repository.PersonRep;
import com.neo.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebTest {


	
	@Resource
	private UserService userService;
	
	@Resource
	private  PersonRep personRep;
	
	@Resource
	private CarRep carRep;
	
	@Test
	public void contextLoads() {
		List<Person> persons = personRep.findAll();
		
		for(Person p : persons) {
			System.out.print("id:" + p.getId() + " ");
			System.out.print("name:" + p.getName());
			System.out.print(" sex: " + p.getSex());
			System.out.println();
			Set<Car> cars = p.getCars();
			for(Car car : cars) {
				System.out.print(" " + car.getName() + "  ");
			}
			
			System.out.println("----------------------------");
		}
	}
	
	@Test
	public void contextLoads2() {
		Car car1 = new Car(12L, "a", 19.3, "P1005");
		Car car2 = new Car(13L, "b", 19.3, "P1005");
		Car car3 = new Car(14L, "c", 19.3, "P1005");
		Car car4 = new Car(15L, "d", 19.3, "P1005");
		
		Set<Car> cars = new HashSet<Car>();
		cars.add(car4);
		cars.add(car3);
		cars.add(car2);
		cars.add(car1);
		
		Person person = new Person("P1005", "guagua", '1', 32, cars);
		
		personRep.save(person);
		
	}
	
		
		


}
