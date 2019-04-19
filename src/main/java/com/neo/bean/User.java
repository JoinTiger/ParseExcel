package com.neo.bean;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User  {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    //用户名(必填)
    @Column(nullable = false, unique = true)
    private String username;
    
//    //姓名(必填)
//    @Column(nullable = true, unique = true)
//    private String nickName;
//    
//    //email
//    @Column(nullable = false, unique = true)
//    private String email;
//    
//    //手机
//    @Column(nullable = true)
//    private String phone;
//    
    //密码
    @Column(nullable = false)
    private String password;
//    
//    //岗位类别
//    @Column(nullable=true)
//    private String job;
//    
//    //所属单位
//    @Column(nullable=true)
//    private String company;
//    
//    //所属部门
//    @Column(nullable=true)
//    private String dept;
//    
//    //所属组织
//    @Column(nullable=true)
//    private String organization;
    
    //日期
    @Column(nullable = false)
    private Date regTime;

    public User() {
    }

	public User(Long id, String username, String password, Date regTime) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.regTime = regTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getRegTime() {
		return regTime;
	}

	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}

    
	
	
	
	
	
}	