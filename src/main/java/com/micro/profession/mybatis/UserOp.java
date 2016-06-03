package com.micro.profession.mybatis;

public interface UserOp {

	
	public void addUser(User user);

	public void updateUser(User user);

	public void deleteUser(int id);

	public User getUser(int id);

}
