package com.pharmc.user.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    
    public String getDetailsById(long id) {
        UserDetailsModel userDetails = userDetailsRepository.findUserById(id);
        if(userDetails !=null) {
            return userDetails.toString();
        } else {
            ObjectNode response = mapper.createObjectNode();
            response.put("status", "no user exists");
            return response.toString();
        }
    }
    
    public String registerUser(UserDetailsModel userDetailRequest) {
        JSONObject checkEmailObject = checkUniqueEmail(userDetailRequest.getEmail());
        ObjectNode response = mapper.createObjectNode();
        if(checkEmailObject.get("ifEmailExists").equals("false")) {
            UserDetailsModel registerUserData = userDetailsRepository.save(userDetailRequest);
            System.out.println("registerUserData is "+registerUserData);
            if(registerUserData == null) {
                response.put("error_msg","fail");
            }else {
                response.put("userdata", registerUserData.toString());
            }
        }else {
            response.put("error_msg","emailAlreadyExists");
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
                response.put("userdetails", userDetails.toString());
            }else {
                response.put("error_msg", "invalid password");
            }
        } else {
            response.put("error_msg", "no user exists");
        }
        return response.toString();
    }

    public UserDetailsModel findById(long id) {
        return userDetailsRepository.findUserById(id);
    }
    @Transactional
    public String updateUser(UserDetailsModel userDetailRequest) {
        System.out.println("userDetailrequest "+findById(userDetailRequest.getId()));
        ObjectNode response = mapper.createObjectNode();
        if(findById(userDetailRequest.getId()) != null) {
            userDetailsRepository.updateUserInfo(userDetailRequest.getId(),userDetailRequest.getEmail(),userDetailRequest.getFirstName(),userDetailRequest.getGender(),userDetailRequest.getLastName(),userDetailRequest.getMobileNo());
            response.put("status","success");
        } else {
            response.put("status","fail");
        }
        return response.toString();
    }
    @Transactional
    public String DeletUserInfo(long id) {
        ObjectNode response = mapper.createObjectNode();
        if(findById(id) != null) {
            userDetailsRepository.deletUserInfo(id);
            response.put("status","success");
        } else {
            response.put("status","fail");
        }
        return response.toString();   
    }
}
