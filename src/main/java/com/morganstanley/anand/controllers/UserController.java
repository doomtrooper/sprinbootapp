package com.morganstanley.anand.controllers;

import com.morganstanley.anand.model.User;
import com.morganstanley.anand.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value="/user", method = RequestMethod.GET)
    public List listUser(){
        return userRepository.findAll();
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public User create(@RequestBody User user){
        return userRepository.save(user);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable(value = "id") long id){
        userRepository.deleteById(id);
        return "success";
    }

}