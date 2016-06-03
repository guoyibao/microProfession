package com.micro.profession.mybatis.resultMapTest;

import java.util.List;

public class User {

	private int id;
	private String userName;
	private String corp1;
	private List<Course> courses;

	public User(Integer id, String userName, String corp) {
		this.userName = userName;
		this.id = id;
		this.corp1 = corp;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
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
		return corp1;
	}

	public void setCorp(String corp) {
		this.corp1 = corp;
	}

}
