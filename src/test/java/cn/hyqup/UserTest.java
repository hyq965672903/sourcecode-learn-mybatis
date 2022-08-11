package cn.hyqup;

import cn.hyqup.domain.User;
import cn.hyqup.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Copyright © 2022 灼华. All rights reserved.
 *
 * @author create by hyq
 * @version 0.1
 * @date 2022/8/10
 * @description:
 */
public class UserTest {
    /**
     * 使用MyBatis API方式
     * @throws IOException
     */
    @Test
    public void test1() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession session = sqlSessionFactory.openSession();
        try {
            User user = (User) session.selectOne("cn.hyqup.mapper.UserMapper.selectAll", 1);
            System.out.println(user);
        } finally {
            session.close();
        }
    }
    /**
     * 通过 SqlSession.getMapper(XXXMapper.class)  接口方式
     */
    @Test
    public void test2() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession session = sqlSessionFactory.openSession(); // ExecutorType.BATCH
        try {
            UserMapper mapper = session.getMapper(UserMapper.class);

            List<User> users = mapper.selectAll();
            System.out.println(users);
        } finally {
            session.close();
        }
    }

}
