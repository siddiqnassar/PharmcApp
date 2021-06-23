package com.pharmc.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pharmc.user.model.UserDetailsModel;
import com.pharmc.user.service.UserDetailsService;

@RestController
@RequestMapping("/user")
public class UserDetailsController {
    @Autowired
    UserDetailsService userDetailsService;
    
    @GetMapping("/{id}")
    public ResponseEntity<String> getUser(@PathVariable long id){
        return new ResponseEntity<String>(userDetailsService.getDetailsById(id).toString(), HttpStatus.OK);
    }
    @PostMapping("/")
    public ResponseEntity<String> registerUser(@RequestBody UserDetailsModel userDetailRequest){
        userDetailRequest.setPassword(new BCryptPasswordEncoder(10).encode(userDetailRequest.getPassword()));
        return new ResponseEntity<String>(userDetailsService.registerUser(userDetailRequest).toString(), HttpStatus.OK);
    }
    @PutMapping("/")
    public ResponseEntity<String> updateUser(@RequestBody UserDetailsModel userDetailRequest){
        //userDetailRequest.setPassword(new BCryptPasswordEncoder(10).encode(userDetailRequest.getPassword()));
        return new ResponseEntity<String>(userDetailsService.updateUser(userDetailRequest).toString(), HttpStatus.OK);
    }
    @PutMapping("/changepwd")
    public ResponseEntity<String> updatePassword(@RequestBody UserDetailsModel userDetailRequest){
        userDetailRequest.setPassword(new BCryptPasswordEncoder(10).encode(userDetailRequest.getPassword()));
        return new ResponseEntity<String>(userDetailsService.changePassword(userDetailRequest).toString(), HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserDetailsModel userDetailRequest){
        userDetailRequest.setAccountId(1);
        userDetailRequest.setDeliveryId(1);
        return new ResponseEntity<String>(userDetailsService.checkCredentials(userDetailRequest).toString(), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id){
        System.out.println("id is "+id);
        return new ResponseEntity<String>(userDetailsService.DeletUserInfo(id).toString(), HttpStatus.OK);
    }
}
