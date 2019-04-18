package com.neo.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="subRecord")
public class SubRecord {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	
	

	private String macSn;
	
	
	private String sVNum;

//	private String motorNum;
	


	public SubRecord() {
		super();
	}

	
	public SubRecord(Long id, String macSn, String sVNum) {
		super();
		this.id = id;
		this.macSn = macSn;
		this.sVNum = sVNum;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMacSn() {
		return macSn;
	}

	public void setMacSn(String macSn) {
		this.macSn = macSn;
	}

	public String getsVNum() {
		return sVNum;
	}

	public void setsVNum(String sVNum) {
		this.sVNum = sVNum;
	}
	

	
	
	
	
	
	
	
}
