package com.micro.profession.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HelloJDBC {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static String DB_URL = "jdbc:mysql://localhost/cloud_study";
	static final String USER = "root";
	static final String PASSWORD = "123456";

	public static void helloword() throws ClassNotFoundException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		// 1. װ����������
		Class.forName(JDBC_DRIVER);
		// 2. �������ݿ�����
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			// 3. ִ��SQL���
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select userName from user");
			// 4. ��ȡִ�н��
			while (rs.next()) {
				System.out.println("Hello" + rs.getString("userName"));
			}
		} catch (SQLException e) {
			// �쳣����
			e.printStackTrace();
		} finally {
			// 5. ������
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

	}

	public static void main(String[] args) throws ClassNotFoundException {
		helloword();
	}

}
