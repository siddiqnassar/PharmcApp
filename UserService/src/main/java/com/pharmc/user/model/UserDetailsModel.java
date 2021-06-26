package com.pharmc.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_details")
public class UserDetailsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;
    
    @Column(name = "email",unique = true)
    private String email;
    
    @Column(name = "password")
    private String password;
    
    @Column(name = "mobileNo")
    private long mobileNo;
    
    @Column(name = "gender")
    private String gender;
    
    @Column(name = "deliveryId")
    private long deliveryId;
    
    @Column(name = "accountId")
    private long accountId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(long mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public long getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(long deliverId) {
        this.deliveryId = deliverId;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    @Override
    public String toString() {
        return "{\"id\": \"" + id + "\",\"firstName\": \"" + firstName + "\",\"lastName\": \"" + lastName + "\",\"email\": \"" + email
                + "\",\"mobileNo\": \"" + mobileNo + "\",\"gender\": \"" + gender + "\",\"deliveryId\": \""
                + deliveryId + "\",\"accountId\": \"" + accountId + "\"}";
    }

}
