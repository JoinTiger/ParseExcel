package com.neo.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.neo.bean.Record;
import com.neo.repository.RecordRep;

@Service
public class RecordService implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 310976488816930L;
	@Resource
	private RecordRep recordRep;
	
	@Transactional
	@Modifying
	@Query
	public void save(Record record) {
		Date date = new Date();
		record.setTime(date);
		recordRep.save(record);
	}
	
	@Transactional
	@Modifying
	@Query
	public void saveAll(List<Record> records) {
		Date date = new Date();
		for(Record record : records) {
			record.setTime(date);
		}
		recordRep.saveAll(records);
	}
	
	
	@Transactional
	@Query
	public List<Record> findAll() {
		List<Record> records = recordRep.findAll();
		return records;
	}
	
	
	
}
