package com.vermouthx.dao;

import com.vermouthx.entity.User;
import com.vermouthx.mapper.UserMapper;
import com.vermouthx.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class UserDao {
    public List<User> getUser(User user) {
        List<User> users = null;
        try (SqlSession session = MyBatisUtil.createSession()) {
            users = session.getMapper(UserMapper.class).getUser(user);
        }
        return users;
    }

    public boolean addUser(User user) {
        int result = 0;
        try (SqlSession session = MyBatisUtil.createSession()) {
            result = session.getMapper(UserMapper.class).addUser(user);
        }
        return result > 0;
    }

    public boolean updateUserScore(User user) {
        int result = 0;
        try (SqlSession session = MyBatisUtil.createSession()) {
            result = session.getMapper(UserMapper.class).updateUserScore(user);
        }
        return result > 0;
    }
}
