package com.vermouthx.controller;

import com.vermouthx.service.UserService;

public class LoginController {

    private UserService userService;

    public LoginController() {
        userService = new UserService();
    }
}
