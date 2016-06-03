package com.micro.profession.mybatis.resultMapTest;

public interface UserOp {
	public User getUser(int id);

	public User getUserByName(int id, String userName);
	
	public String helloUser(int id );

}
