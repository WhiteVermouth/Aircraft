package com.vermouthx.service;

import com.vermouthx.dao.UserDao;
import com.vermouthx.entity.User;
import com.vermouthx.exception.UserException;

import java.util.List;
import java.util.regex.Pattern;

public class UserService {
    private UserDao userDao;

    public UserService() {
        userDao = new UserDao();
    }

    /**
     * user register
     * @param user
     * @return
     * @throws UserException
     */
    public boolean register(User user) throws UserException {
        String userName = user.getUserName();
        String userPassword = user.getUserPassword();

        String userNameRegex = "^[\u4e00-\u9fa5]{0,10}$";
        String userPasswordRegex = "^[a-zA-Z]\\w{5,17}$";

        boolean userNameIsRight = Pattern.matches(userNameRegex, userName);
        boolean userPasswordIsRight = Pattern.matches(userPasswordRegex,
                userPassword);
        if (!userNameIsRight) {
            throw new UserException("用户名格式错误");
        }
        if (!userPasswordIsRight) {
            throw new UserException("密码格式错误");
        }
        return userDao.addUser(user);
    }

    /**
     * user register
     * @param user
     * @return
     * @throws UserException
     */
    public User login(User user) throws UserException {
        String userNameRegex = "^[\u4e00-\u9fa5]{0,10}$";
        String userPasswordRegex = "^[a-zA-Z]\\w{5,17}$";

        boolean userNameIsRight = Pattern.matches(userNameRegex, user.getUserName());
        boolean userPasswordIsRight = Pattern.matches(userPasswordRegex,
                user.getUserPassword());
        if (!userNameIsRight) {
            throw new UserException("用户名格式错误");
        }
        if (!userPasswordIsRight) {
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
