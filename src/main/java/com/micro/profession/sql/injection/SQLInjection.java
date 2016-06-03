package com.micro.profession.sql.injection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLInjection {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static String DB_URL = "jdbc:mysql://localhost/cloud_study";
	static final String USER = "root";
	static final String PASSWORD = "123456";

	public static User login(String userName, String password)
			throws ClassNotFoundException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		User user = null;

		// 1. 装载驱动程序
		Class.forName(JDBC_DRIVER);
		// 2. 建立数据库连接
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			// 3. 执行SQL语句
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from user where userName = '"
					+ userName + "' and password = '" + password + "'");
			// 4. 获取执行结果
			while (rs.next()) {
				user = new User();
				user.setUserName(rs.getString("userName"));
				user.setCorp(rs.getString("corp"));
			}
		} catch (SQLException e) {
			// 异常处理
			e.printStackTrace();
		} finally {
			// 5. 清理环境
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// ignore
			}

		}
		return user;

	}

	public static void main(String[] args) throws ClassNotFoundException {
		System.out.println("Start verify sql injection risk...");
		System.out.println("input correct username and password...");
		System.out.println(login("ZhangSan", "123456") != null);
		System.out.println("verify sucess...");
		System.out.println("input incorrect username and password...");
		System.out.println(login("ZhangSan';-- ","aaa") != null);
		System.out.println("also verify sucess...");
	}
}
