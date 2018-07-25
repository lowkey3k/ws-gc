package com.lht.demo.web.controller;


import com.lht.demo.web.entity.User;
import com.lht.demo.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

//    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private UserService userService;

    @GetMapping("/get")
    public List<User> getALl(){
        return userService.getAll();
    }

}
