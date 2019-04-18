package com.neo.test;



import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.neo.bean.Record;
import com.neo.repository.CarRep;
import com.neo.repository.PersonRep;
import com.neo.repository.RecordRep;
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
	
	@Resource
	private RecordRep recordRep;
	
	@Test
	public void contextLoads() {
		List<Record> findAll = recordRep.findAll();
	}
	

	
		
		


}
