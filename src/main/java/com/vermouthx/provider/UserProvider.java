package com.vermouthx.provider;

import org.apache.ibatis.jdbc.SQL;

public class UserProvider {
    public String getUserProvider() {
        return new SQL() {
            {
                SELECT("*");
                FROM("users");
                WHERE("user_name=#{name} and user_password=#{password}");
            }
        }.toString();
    }

    public String addUserProvider() {
        return new SQL() {
            {
                INSERT_INTO("users");
                VALUES("user_name", "#{name}");
                VALUES("user_password", "#{password}");
            }
        }.toString();
    }

    public String updateUserScoreProvider() {
        return new SQL() {
            {
                UPDATE("users");
                SET("score=#{score}");
                WHERE("user_id=#{id} and score<#{score}");
            }
        }.toString();
    }
}
