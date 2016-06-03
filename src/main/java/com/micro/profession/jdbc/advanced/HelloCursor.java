package com.micro.profession.jdbc.advanced;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HelloCursor {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static String DB_URL = "jdbc:mysql://localhost/cloud_study";
	static final String USER = "root";
	static final String PASSWORD = "123456";

	public static void helloCursor() throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;

		// 1. 装载驱动程序
		Class.forName(JDBC_DRIVER);
		// 2. 建立数据库连接
		try {
			DB_URL = DB_URL + "?useCursorFetch=true";
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			// 3. 执行SQL语句
			ptmt = conn.prepareStatement("select userName from user");
			ptmt.setFetchSize(1);
			rs = ptmt.executeQuery();
			// 4. 获取执行结果
			while (rs.next()) {
				System.out.println("Hello" + rs.getString("userName"));
			}
		} catch (SQLException e) {
			// 异常处理
			e.printStackTrace();
		} finally {
			// 5. 清理环境
			try {
				if (rs != null)
					rs.close();
				if (ptmt != null)
					ptmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// ignore
			}

		}

	}

	public static void main(String[] args) throws ClassNotFoundException {
		helloCursor();
	}
}
