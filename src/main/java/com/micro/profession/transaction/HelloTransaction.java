package com.micro.profession.transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Savepoint;

import org.apache.commons.dbcp2.BasicDataSource;

public class HelloTransaction {
	public static BasicDataSource ds = null;
	static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	static String DB_URL = "jdbc:mysql://localhost/cloud_study";
	static final String USER_NAME = "root";
	static final String PASSWORD = "123456";

	public static void transferAccount() throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement ptmt = null;
		try {
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			ptmt = conn
					.prepareStatement("update user set account = ? where userName = ?");
			ptmt.setInt(1, 0);
			ptmt.setString(2, "ZhangSan");
			ptmt.execute();
			ptmt.setInt(1, 100);
			ptmt.setString(2, "LiSi");
			ptmt.execute();
			conn.commit();
		} catch (SQLException e) {
			if (conn != null)
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (ptmt != null)
					ptmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void savePointTest() {
		Connection conn = null;
		PreparedStatement ptmt = null;
		Savepoint sp = null;
		try {
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			ptmt = conn
					.prepareStatement("update user set account = ? where userName = ?");
			ptmt.setInt(1, 0);
			ptmt.setString(2, "ZhangSan");
			ptmt.execute();
			sp = conn.setSavepoint();
			ptmt.setInt(1, 100);
			ptmt.setString(2, "LiSi");
			ptmt.execute();
			throw new SQLException();
		} catch (SQLException e) {
			if (conn != null)
				try {
					conn.rollback(sp);
					ptmt.setInt(1, 100);
					ptmt.setString(2, "ZhaoWu");
					ptmt.execute();
					conn.commit();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (ptmt != null)
					ptmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void dbpoolInit() {
		ds = new BasicDataSource();
		ds.setDriverClassName(DRIVER_NAME);
		ds.setUrl(DB_URL);
		ds.setUsername(USER_NAME);
		ds.setPassword(PASSWORD);
		ds.setMaxTotal(2);
	}

	public static void main(String[] args) throws ClassNotFoundException {
		dbpoolInit();
		transferAccount();
		//savePointTest();
	}

}
