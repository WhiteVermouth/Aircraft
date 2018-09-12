package com.vermouthx.provider;

import com.vermouthx.entity.User;
import org.apache.ibatis.jdbc.SQL;

public class UserProvider {
    public String getUserProvider(User user) {
        return new SQL() {
            {
                SELECT("*");
                FROM("users");
                WHERE("user_name=#{name} and user_password=#{password}");
            }
        }.toString();
    }

    public String addUserProvider(User user) {
        return new SQL() {
            {
                INSERT_INTO("users");
                VALUES("user_name", "#{name}");
                VALUES("user_password", "#{password}");
            }
        }.toString();
    }

    public String updateUserScoreProvider(User user) {
        return new SQL() {
            {
                UPDATE("users");
                SET("score=#{score}");
                WHERE("user_id=#{id} and score<#{score}");
            }
        }.toString();
    }
}
