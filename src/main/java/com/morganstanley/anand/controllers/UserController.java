package com.morganstanley.anand.controllers;

import com.morganstanley.anand.model.User;
import com.morganstanley.anand.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping(value="/user")
    public List<User> listUser(){
        return userRepository.findAll();
    }

    @PostMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public User create(@Valid @RequestBody User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }


    @DeleteMapping(value = "/user/{id}")
    public String delete(@PathVariable(value = "id") long id){
        userRepository.deleteById(id);
        return "success";
    }

    @GetMapping(value = "/user/{email}")
    public User getUserByEmail(@PathVariable(value = "email") String email){
        return userRepository.findByEmail(email);
    }

}