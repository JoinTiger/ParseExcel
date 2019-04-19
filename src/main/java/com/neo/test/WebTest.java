package com.neo.test;



import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.neo.bean.Record;
import com.neo.bean.SubRecord;
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
		
		Record record1 = new Record();
		Record record2 = new Record();
		Record record3 = new Record();
		Record record4 = new Record();
		
		SubRecord subRecord1 = new SubRecord();
		SubRecord subRecord2 = new SubRecord();
		SubRecord subRecord3 = new SubRecord();
		SubRecord subRecord4 = new SubRecord();
		
		record1.setMacSn("1001");
		record2.setMacSn("1002");
		record3.setMacSn("1003");
		record4.setMacSn("1004");
		
		subRecord1.setRecord(record1);
		subRecord1.setsVNum("one");
		subRecord2.setRecord(record1);
		subRecord2.setsVNum("two");
		
		subRecord3.setRecord(record3);
		subRecord3.setsVNum("three");
		
		subRecord4.setRecord(record4);
		subRecord4.setsVNum("four");
		
		record3.getSubs().add(subRecord3);
		record4.getSubs().add(subRecord4);
		
		record1.getSubs().add(subRecord1);
		record1.getSubs().add(subRecord2);
		
		
		recordRep.save(record1);
		recordRep.save(record3);
		recordRep.save(record4);
		
		List<Record> records = recordRep.findAll();
		
		for(Record r :records) {
			System.out.print("macSn" + r.getMacSn());
			Set<SubRecord> subs = r.getSubs();
			
			System.out.println();
			
			System.out.println("subs:");
			for(SubRecord sub : subs) {
				System.out.print(sub.getsVNum() + "      ");
			}
			
			System.out.println("-----------------");
			
		}
		
		
		
	}
	

	
		
		


}
