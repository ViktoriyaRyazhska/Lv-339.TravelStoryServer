package com.travelstory.dto;

import com.travelstory.entity.User;

public class RegistrationDTO {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private User.Gender gender;

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

    public RegistrationDTO(String email, String password, String firstName, String lastName, User.Gender gender) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

    public User.Gender getGender() {
        return gender;
    }

    public RegistrationDTO() {
    }

    public void setGender(User.Gender gender) {
        this.gender = gender;
    }
}
