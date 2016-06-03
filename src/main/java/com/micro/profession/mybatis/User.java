package com.micro.profession.mybatis;

public class User {
	private int id;
	private String userName;
	private String corp;
	private String password;
	private int accout;

	public User(Integer id, String userName, String corp) {
		this.id = id;
		this.userName = userName;
		this.corp = corp;
	}

	public User(String userName, String corp) {
		this.userName = userName;
		this.corp = corp;
	}
	
	public User(Integer id,String userName,String corp,String password,Integer account) {
		this.userName = userName;
		this.corp = corp;
		this.accout = account;
		this.password =password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCorp() {
		return corp;
	}

	public void setCorp(String corp) {
		this.corp = corp;
	}

}
