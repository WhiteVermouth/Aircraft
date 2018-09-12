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

    @Test
    public void testGetUser() {
        UserService service = new UserService();
        try {
            User u = service.login(new User("好","a123124124"));
            System.out.println(u.getId());
            System.out.println(u.getName());
            System.out.println(u.getPassword());
            System.out.println(u.getScore());
        } catch (UserException e) {
            e.printStackTrace();
        }
    }
}
