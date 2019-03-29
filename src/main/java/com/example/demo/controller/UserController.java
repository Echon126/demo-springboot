package com.example.demo.controller;

import com.example.demo.entity.User;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author admin
 * @date 2019-3-18 11:44
 */
@Controller
public class UserController {
    @PostMapping(value = "/user",
            consumes = MediaType.APPLICATION_XML_VALUE,
            produces = MediaType.APPLICATION_XML_VALUE)
    public User create(@RequestBody User user) {
        user.setName("didispace.com:" + user.getName());
        user.setAge(user.getAge() + 100);
        return user;
    }
}
