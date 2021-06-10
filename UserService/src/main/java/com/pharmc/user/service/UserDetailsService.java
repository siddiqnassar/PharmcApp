package com.pharmc.user.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.pharmc.user.model.UserDetailsModel;
import com.pharmc.user.repository.UserDetailsRepository;

@Service
public class UserDetailsService{

    @Autowired
    UserDetailsRepository userDetailsRepository;
    @Autowired
    private ObjectMapper mapper;
    
    public String save(UserDetailsModel userDetailRequest) {
        JSONObject checkEmailObject = checkUniqueEmail(userDetailRequest.getEmail());
        ObjectNode response = mapper.createObjectNode();
        if(checkEmailObject.get("ifEmailExists").equals("false")) {
            UserDetailsModel savedData = userDetailsRepository.save(userDetailRequest);
            System.out.println("savedData is "+savedData);
            if(savedData == null) {
                response.put("status","fail"); 
            }else {
                response.put("status","success");
            }
        }else {
            response.put("status","emailAlreadyExists");
        }
        return response.toString();
    }

    public JSONObject checkUniqueEmail(String email) {
        JSONObject response = new JSONObject();
        if(userDetailsRepository.getUserDetailsEmail(email) == null) {
            response.put("ifEmailExists", "false");
        }else {
            response.put("ifEmailExists", "true");
        }
        return response;
    }

    public String checkCredentials(UserDetailsModel userDetailRequest) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        ObjectNode response = mapper.createObjectNode();
        UserDetailsModel userDetails = userDetailsRepository.getUserDetailsEmail(userDetailRequest.getEmail());
        if(userDetails !=null) {
            Boolean isPasswordMatched = encoder.matches(userDetailRequest.getPassword(), userDetails.getPassword());
            if(isPasswordMatched == true) {
                response.put("loginStatus", "success");
            }else {
                response.put("loginStatus", "invalid password");
            }
        } else {
            response.put("loginStatus", "no user exists");
        }
        return response.toString();
    }
}
