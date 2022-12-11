package global.scit.cosmetic.dao;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisConfig {
	private static SqlSessionFactory sqlSessionFactory;
	
	//static 블럭 --> 생성자와 유사, static 맴버를 초기화 하는 용도로 사용
	static {
		//mybatis-config.xml 을 읽어와서 Oracle과 연결하는 작업 
		String resource = "mybatis-config.xml";
		
		try {
			Reader reader = Resources.getResourceAsReader(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			reader.close();
		} catch(Exception e ) {
			e.printStackTrace();
		}
	}
	public static SqlSessionFactory getSessionFactory() {
		return sqlSessionFactory;
	}
}
