package com.vermouthx.service;

import com.vermouthx.dao.UserDao;
import com.vermouthx.entity.User;
import com.vermouthx.exception.UserException;

import java.util.regex.Pattern;

public class UserService {
    private UserDao userDao;

    public UserService() {
        userDao = new UserDao();
    }

    /**
     * user register
     *
     * @param user
     * @return
     * @throws UserException
     */
    public boolean register(User user) throws UserException {
        String userNameRegex = "^[\u4e00-\u9fa5]{0,10}$";
        String userPasswordRegex = "^[a-zA-Z]\\w{5,17}$";
        if (!Pattern.matches(userNameRegex, user.getName())) {
            throw new UserException("用户名格式错误");
        }
        if (!Pattern.matches(userPasswordRegex, user.getPassword())) {
            throw new UserException("密码格式错误");
        }
        return userDao.addUser(user);
    }

    /**
     * user register
     *
     * @param user
     * @return
     * @throws UserException
     */
    public User login(User user) throws UserException {
        String userNameRegex = "^[\u4e00-\u9fa5]{0,10}$";
        String userPasswordRegex = "^[a-zA-Z]\\w{5,17}$";
        if (!Pattern.matches(userNameRegex, user.getName())) {
            throw new UserException("用户名格式错误");
        }
        if (!Pattern.matches(userPasswordRegex, user.getPassword())) {
            throw new UserException("密码格式错误");
        }
        return userDao.getUser(user).get(0);
    }

    /*
     *更新成绩功能
     */
    public boolean updateScore(User user) {
        return userDao.updateUserScore(user);
    }
}
