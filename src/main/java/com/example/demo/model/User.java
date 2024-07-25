package com.example.demo.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class User {

    @Size(min = 3,max = 16,message = "Enter minimum 3 characters")
    String username;

    @NotBlank(message = "Should not be empty")
    String password;

    @Email(message = "Enter valid email address")
    String email;

    @NotBlank(message = "Should not be empty")
    String phoneNumber;

    @NotBlank(message = "Should not be empty")
    String countryCode;

    public User(String username, String password) {
        this.username=username;
        this.password=password;
    }

    public User(String username, String password, String email, String phoneNumber, String countryCode) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.countryCode = countryCode;
    }

    public User() {

    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
