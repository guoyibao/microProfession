package com.micro.profession.jdbc.advanced;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

public class HelloBatch {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/cloud_study";
	static final String USER = "root";
	static final String PASSWORD = "123456";

	public static void insertUsers(Set<String> users)
			throws ClassNotFoundException {
		Connection conn = null;
		Statement stmt = null;

		// 1. װ����������
		Class.forName(JDBC_DRIVER);
		// 2. �������ݿ�����
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			// 3. ִ��SQL���
			stmt = conn.createStatement();
			for (String user : users) {
				stmt.addBatch("insert into user(userName) values('" + user
						+ "')");
			}
			stmt.executeBatch();
			stmt.clearBatch();
		} catch (SQLException e) {
			// �쳣����
			e.printStackTrace();
		} finally {
			// 5. ������
			try {
				if (conn != null)
					conn.close();
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				// ignore
			}

		}

	}

	public static void main(String[] args) throws ClassNotFoundException {
		Set<String> users = new HashSet<String>();
		users.add("GuoYi");
		users.add("ZhangSi");
		users.add("LiSan");
		insertUsers(users);
	}
}
