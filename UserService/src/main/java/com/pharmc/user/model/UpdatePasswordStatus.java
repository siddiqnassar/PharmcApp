package com.pharmc.user.model;

import org.springframework.stereotype.Component;

@Component
public class UpdatePasswordStatus {

    private String updatePasswordStatus;
    public String getUpdatePasswordStatus() {
        return updatePasswordStatus;
    }
    public void setUpdatePasswordStatus(String updatePasswordStatus) {
        this.updatePasswordStatus = updatePasswordStatus;
    }
    @Override
    public String toString() {
        return "{\"updatePasswordStatus\": \"" + updatePasswordStatus + "\"}";
    }
}
