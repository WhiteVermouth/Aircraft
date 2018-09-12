package com.vermouthx.provider;

import com.vermouthx.entity.User;

public class UserProvider {
    public String getUserProvider(User user) {
        return "SELECT * FROM users WHERE user_name = '" + user.getUserName() + "' AND user_password = '" + user.getUserPassword() + "'";
    }

    public String addUserProvider(User user) {
        return "INSERT INTO users (user_name,user_password) VALUES ('" + user.getUserName() + "','" + user.getUserPassword() + "')";
    }

    public String updateUserScoreProvider(User user) {
        return "UPDATE users SET score = " + user.getScore() + " WHERE user_id = " + user.getUserId() + " AND score < " + user.getScore();
    }
}
