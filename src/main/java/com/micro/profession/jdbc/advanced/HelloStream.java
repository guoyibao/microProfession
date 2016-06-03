package com.micro.profession.jdbc.advanced;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HelloStream {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static String DB_URL = "jdbc:mysql://localhost/cloud_study";
	static String FILE_URL = "/Users/guoyi/NeteaseWork／blog";
	static final String USER = "root";
	static final String PASSWORD = "123456";

	public static void helloStream() throws ClassNotFoundException, IOException {
		Connection conn = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;

		// 1. 装载驱动程序
		Class.forName(JDBC_DRIVER);
		// 2. 建立数据库连接
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			// 3. 执行SQL语句
			ptmt = conn.prepareStatement("select * from user_note");
			rs = ptmt.executeQuery();
			// 4. 获取执行结果
			while (rs.next()) {
				InputStream in = rs.getBinaryStream("user_note");
				File f = new File(FILE_URL);
				OutputStream out = null;
				out = new FileOutputStream(f);
				int temp = 0;
				while ((temp = in.read()) != -1) {
					out.write(temp);
				}
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

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		helloStream();
	}
}
