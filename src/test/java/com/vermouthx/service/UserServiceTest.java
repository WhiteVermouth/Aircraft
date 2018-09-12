package com.vermouthx.service;

import com.vermouthx.entity.User;
import com.vermouthx.exception.UserException;
import org.junit.Test;

public class UserServiceTest {
    @Test
    public void testAddUser() {
        User u = new User("好", "a123124124");
        UserService service = new UserService();
        try {
            service.register(u);
        } catch (UserException e) {
            e.printStackTrace();
        }
    }
}
