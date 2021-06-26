package com.pharmc.user.model;

import org.springframework.stereotype.Component;

@Component
public class RegisterResponse {

    private long id;
    private String registrationStatusMessage;
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getRegistrationStatusMessage() {
        return registrationStatusMessage;
    }
    public void setRegistrationStatusMessage(String registrationStatusMessage) {
        this.registrationStatusMessage = registrationStatusMessage;
    }
    @Override
    public String toString() {
        return "{\"id\": \"" + id + "\",\"registrationStatusMessage\": \"" + registrationStatusMessage +  "\"}";
    }
}
