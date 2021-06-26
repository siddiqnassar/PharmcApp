package com.pharmc.user.model;

import org.springframework.stereotype.Component;

@Component
public class LoginResponse {

    private long id;
    private String loginStatusMessage;
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getLoginStatusMessage() {
        return loginStatusMessage;
    }
    public void setLoginStatusMessage(String loginStatusMessage) {
        this.loginStatusMessage = loginStatusMessage;
    }
    @Override
    public String toString() {
        return "{\"id\": \"" + id + "\",\"loginStatusMessage\": \"" + loginStatusMessage +  "\"}";
    }
}
