package com.teamone.unitask.controller;

import com.teamone.unitask.model.User;
import com.teamone.unitask.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // get a single user object from front end and store in the database unitask-user;
//    @PostMapping("/postUser")
//    User postNewUser(@RequestBody User newUser) {
//        return userRepository.save(newUser);
//    }

    //
    @GetMapping("/getAllUsers")
    List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/postUserSignin")
    User postUserSignin(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }

//    @GetMapping("/collectLogin")
//    List<String> getLoginInfo()
}
