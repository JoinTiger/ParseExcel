package com.neo.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
public class Record implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -50394004543436756L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	
//	@Column(unique=true, nullable=false)
//	private String batchID;
	
	@Column(name="mac_sn", nullable=false, unique=true)
	private String macSn;
	
	private String nCNum;
	
	private String iPCNum;
	
	private String conctractNum;

	private Date time;
	
	

	@OneToMany(mappedBy="record", cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<SubRecord> subs= new HashSet<SubRecord>();
	
	public Record() {
		super();
	}

	public Record(Long id, String macSn, String nCNum, String iPCNum, String conctractNum, Date time,
			Set<SubRecord> subs) {
		super();
		this.id = id;
		this.macSn = macSn;
		this.nCNum = nCNum;
		this.iPCNum = iPCNum;
		this.conctractNum = conctractNum;
		this.time = time;
		this.subs = subs;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Set<SubRecord> getSubs() {
		return subs;
	}

	public void setSubs(Set<SubRecord> subs) {
		this.subs = subs;
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

	public String getnCNum() {
		return nCNum;
	}

	public void setnCNum(String nCNum) {
		this.nCNum = nCNum;
	}

	public String getiPCNum() {
		return iPCNum;
	}

	public void setiPCNum(String iPCNum) {
		this.iPCNum = iPCNum;
	}

	public String getConctractNum() {
		return conctractNum;
	}

	public void setConctractNum(String conctractNum) {
		this.conctractNum = conctractNum;
	}
	
	
	
}
