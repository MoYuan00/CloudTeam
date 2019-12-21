package com.zq.mvc.test;

import com.zq.mvc.main.mappers.user.LoginMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;

public class TestMapperLogin {
    public static void main(String[] args) {
        File file = Paths.get(System.getProperty("user.dir")).resolve("src/com/zq/mvc/test/mybatis-config.xml").toFile();
        System.out.println(file);

        try {
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(new FileInputStream(file));
            SqlSession sqlSession = sqlSessionFactory.openSession();
            LoginMapper mapperLogin = sqlSession.getMapper(LoginMapper.class);
            System.out.println(mapperLogin == null);
            assert mapperLogin != null;
//            int re = mapperLogin.loginByUserNameAndPassword("1", "1");
//            System.out.println(re);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
