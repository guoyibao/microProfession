package com.micro.profession.mybatis.resultMapTest;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class HelloMyBatis {

	public static void main(String[] args) {
		// 1. ���������ļ���Ŀ¼��ְ
		String resource = "conf.xml";
		// 2. ����Ӧ�������ļ�
		InputStream is = HelloMyBatis.class.getClassLoader()
				.getResourceAsStream(resource);
		// 3. ����SqlSessonFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder()
				.build(is);
		// 4. ��ȡSession
		SqlSession session = sessionFactory.openSession();
		try {
			// 5. ��ȡ������
			UserOp userOp = session.getMapper(UserOp.class);
			// 6. ��ɲ�ѯ����
			//User user = userOp.getUserByName(25,"XiaoMing");
			String s = userOp.helloUser(25);
			System.out.println(s);
			//System.out.println(user.getId() + " " + user.getUserName() + " ");
			//System.out.println(user.getCourses().get(0).getCourseName() + " ");
			//System.out.println(user.getCourses().get(0).getTeacher()
			//		.getTeacherName());
		} finally {
			// 7.�ر�Session
			session.close();
		}
	}
}
