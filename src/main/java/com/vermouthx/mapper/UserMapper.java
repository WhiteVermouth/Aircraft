package com.vermouthx.mapper;

import com.vermouthx.entity.User;
import com.vermouthx.provider.UserProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Results(id = "userMap", value = {
            @Result(property = "userId", column = "user_id"),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "userPassword", column = "user_password"),
            @Result(property = "score", column = "score")
    })
    @SelectProvider(type = UserProvider.class, method = "getUserProvider")
    List<User> getUser(User user);

    @InsertProvider(type = UserProvider.class, method = "addUserProvider")
    int addUser(User user);


    @UpdateProvider(type = UserProvider.class, method = "updateUserScoreProvider")
    int updateUserScore(User user);
}
