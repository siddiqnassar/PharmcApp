package com.pharmc.user.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.pharmc.user.model.LoginResponse;
import com.pharmc.user.model.RegisterResponse;
import com.pharmc.user.model.UpdatePasswordStatus;
import com.pharmc.user.model.UpdateUserStatus;
import com.pharmc.user.model.UserDetailsModel;
import com.pharmc.user.repository.UserDetailsRepository;

@Service
public class UserDetailsService{

    @Autowired
    UserDetailsRepository userDetailsRepository;
    @Autowired
    LoginResponse loginResponse;
    @Autowired
    RegisterResponse registerResponse;
    @Autowired
    UpdateUserStatus updateUserStatus;
    @Autowired
    UpdatePasswordStatus updatePasswordStatus;
    private static final String SUCCESS = "Success";
    private static final String FAIL = "Fail";
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
    
    public RegisterResponse registerUser(UserDetailsModel userDetailRequest) {
        JSONObject checkEmailObject = checkUniqueEmail(userDetailRequest.getEmail());
        if(checkEmailObject.get("ifEmailExists").equals("false")) {
            UserDetailsModel registerUserData = userDetailsRepository.save(userDetailRequest);
            System.out.println("registerUserData is "+registerUserData);
            if(registerUserData == null) {
                registerResponse.setRegistrationStatusMessage(FAIL);
            }else {
                registerResponse.setRegistrationStatusMessage(SUCCESS);
                registerResponse.setId(registerUserData.getId());
            }
        }else {
            registerResponse.setRegistrationStatusMessage("User already exists");
        }
        return registerResponse;
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

    public LoginResponse checkCredentials(UserDetailsModel userDetailRequest) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        UserDetailsModel userDetails = userDetailsRepository.getUserDetailsEmail(userDetailRequest.getEmail());
        if(userDetails !=null) {
            Boolean isPasswordMatched = encoder.matches(userDetailRequest.getPassword(), userDetails.getPassword());
            if(isPasswordMatched == true) {
                loginResponse.setId(userDetails.getId());
                loginResponse.setLoginStatusMessage(SUCCESS);
            }else {
                loginResponse.setLoginStatusMessage("Invalid Pwd");
            }
        } else {
            loginResponse.setLoginStatusMessage(FAIL);
        }
        return loginResponse;
    }

    public UserDetailsModel findById(long id) {
        return userDetailsRepository.findUserById(id);
    }
    @Transactional
    public UpdateUserStatus updateUser(UserDetailsModel userDetailRequest) {
        System.out.println("userDetailrequest "+findById(userDetailRequest.getId()));
        if(findById(userDetailRequest.getId()) != null) {
            int updateFlag = userDetailsRepository.updateUserInfo(userDetailRequest.getId(),userDetailRequest.getFirstName(),userDetailRequest.getLastName(),userDetailRequest.getGender(),userDetailRequest.getMobileNo(),userDetailRequest.getDeliveryId(),userDetailRequest.getAccountId());
            if(updateFlag == 1) {
                updateUserStatus.setUpdateStatus(SUCCESS);
            }else {
                updateUserStatus.setUpdateStatus(FAIL);
            }
        } else {
            updateUserStatus.setUpdateStatus("Invalid User");
        }
        return updateUserStatus;
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

    @Transactional
    public UpdatePasswordStatus changePassword(UserDetailsModel userDetailRequest) {
        if(findById(userDetailRequest.getId()) != null) {
            int updateStatus = userDetailsRepository.changePassword(userDetailRequest.getId(),userDetailRequest.getPassword());
            if(updateStatus == 1) {
                updatePasswordStatus.setUpdatePasswordStatus(SUCCESS);
            }else {
                updatePasswordStatus.setUpdatePasswordStatus(FAIL);
            }
        } else {
            updatePasswordStatus.setUpdatePasswordStatus("Invalid User");
        }
        return updatePasswordStatus;
    }
}
