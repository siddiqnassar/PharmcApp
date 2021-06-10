package com.pharmc.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pharmc.user.model.UserDetailsModel;
import com.pharmc.user.service.UserDetailsService;

@RestController
@RequestMapping("/user")
public class UserDetailsController {
    @Autowired
    UserDetailsService userDetailsService;
    
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDetailsModel userDetailRequest){
        userDetailRequest.setPassword(new BCryptPasswordEncoder(10).encode(userDetailRequest.getPassword()));
        System.out.println("hello");
        //UserDetailsModel loginUserModel = new UserDetailsService(loginRequest.getEmail(), new BCryptPasswordEncoder(10).encode(loginRequest.getPassword()));
        return new ResponseEntity<String>(userDetailsService.save(userDetailRequest).toString(), HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserDetailsModel userDetailRequest){
        userDetailRequest.setAccountId(1);
        userDetailRequest.setDeliveryId(1);
        return new ResponseEntity<String>(userDetailsService.checkCredentials(userDetailRequest).toString(), HttpStatus.OK);
    }
}
