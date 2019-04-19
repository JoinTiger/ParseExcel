package com.neo.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class SubRecord {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	
	private String sVNum;
	
	@ManyToOne(targetEntity = Record.class )
	@JoinColumn(name="mac_sn", referencedColumnName="mac_sn")
	private Record record;

	public SubRecord() {
		super();
	}

	public SubRecord(Long id, String sVNum, Record record) {
		super();
		this.id = id;
		this.sVNum = sVNum;
		this.record = record;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getsVNum() {
		return sVNum;
	}

	public void setsVNum(String sVNum) {
		this.sVNum = sVNum;
	}

	public Record getRecord() {
		return record;
	}

	public void setRecord(Record record) {
		this.record = record;
	}

	
	
	
	
	
	
}
