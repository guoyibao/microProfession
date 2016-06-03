package com.micro.profession.mybatis;

import java.io.InputStream;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class HelloMyBatisMoreOp {

	public static void main(String[] args) {
		moreOp();
	}

	public static void moreOp() {
		// 1. ���������ļ�
		String resource = "conf.xml";
		// 2. ����Ӧ�������ļ�
		InputStream is = HelloMyBatisMoreOp.class.getClassLoader()
				.getResourceAsStream(resource);
		// 3. ����SqlSessonFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder()
				.build(is);

		SqlSession session = sessionFactory.openSession(true);
		try {
			// 5. ��ȡ������
			UserOp userOp = session.getMapper(UserOp.class);
			User user = new User("XiaoMing", "Netease");
			// �����û�
			userOp.addUser(user);
			System.out.println(user.getId());
			// ��ѯ�û�
			user = userOp.getUser(user.getId());
			System.out.println("userId:" + user.getId() + ", userName:"
					+ user.getUserName() + ", corp:" + user.getCorp());
			user.setUserName("LiMing");
			// �����û�
			userOp.updateUser(user);
			// ɾ���û�
			userOp.deleteUser(user.getId());
		} finally {
			// 7.�ر�Session
			session.close();
		}
	}
}
