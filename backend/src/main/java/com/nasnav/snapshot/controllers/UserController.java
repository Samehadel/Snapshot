package com.nasnav.snapshot.controllers;

import com.nasnav.snapshot.entities.User;
import com.nasnav.snapshot.models.UserSignupModel;
import com.nasnav.snapshot.repositories.UserRepository;
import com.nasnav.snapshot.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/users/signup")
    public ResponseEntity signup(@RequestBody UserSignupModel userModel){

        User user = new User();

        BeanUtils.copyProperties(userModel, user);

        userService.registerNewUser(user);

        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/users/drop/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") long id){

        userService.deleteExistingUser(id);

        return ResponseEntity.ok().build();
    }
}
