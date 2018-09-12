package com.vermouthx.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisUtil {
    private static String config = "mybatis/mybatis-config.xml";
    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(ResourceUtil.getResource(config).openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private MyBatisUtil() {

    }

    public static SqlSession createSession() {
        return sqlSessionFactory.openSession(true);
    }

}
