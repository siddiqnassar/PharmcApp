package com.pharmc.user.model;

import org.springframework.stereotype.Component;

@Component
public class UpdateUserStatus {

    private String updateStatus;
    public String getUpdateStatus() {
        return updateStatus;
    }
    public void setUpdateStatus(String updateStatus) {
        this.updateStatus = updateStatus;
    }
    @Override
    public String toString() {
        return "{\"updateStatus\": \"" + updateStatus + "\"}";
    }
}
