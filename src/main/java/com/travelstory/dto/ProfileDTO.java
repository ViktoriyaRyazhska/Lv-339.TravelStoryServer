package com.travelstory.dto;

import com.travelstory.entity.TravelStory;
import com.travelstory.entity.User;
import com.travelstory.entity.UserRole;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ProfileDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private LocalDate dateOfBirth;
    private LocalDateTime registrationDate;
    private LocalDate lastUpdateDate;
    private String profilePic;
    private String backgroundPic;
    private Long countOfTravelStories;
    private String location;
    private String hobbies;
    private User.Gender gender;
    private User.UserStatus status;
    private User.UserState state;
    private UserRole role;

    public ProfileDTO() {
    }

    public ProfileDTO(Long id, String firstName, String lastName, String email, LocalDate dateOfBirth, LocalDateTime registrationDate, String profilePic, String backgroundPic, Long countOfTravelStories, String location, String hobbies, User.Gender gender, User.UserStatus status, User.UserState state, UserRole role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.registrationDate = registrationDate;
        this.profilePic = profilePic;
        this.backgroundPic = backgroundPic;
        this.countOfTravelStories = countOfTravelStories;
        this.location = location;
        this.hobbies = hobbies;
        this.gender = gender;
        this.status = status;
        this.state = state;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getBackgroundPic() {
        return backgroundPic;
    }

    public void setBackgroundPic(String backgroundPic) {
        this.backgroundPic = backgroundPic;
    }

    public Long getCountOfTravelStories() {
        return countOfTravelStories;
    }

    public void setCountOfTravelStories(Long countOfTravelStories) {
        this.countOfTravelStories = countOfTravelStories;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public User.Gender getGender() {
        return gender;
    }

    public void setGender(User.Gender gender) {
        this.gender = gender;
    }

    public User.UserStatus getStatus() {
        return status;
    }

    public void setStatus(User.UserStatus status) {
        this.status = status;
    }

    public User.UserState getState() {
        return state;
    }

    public void setState(User.UserState state) {
        this.state = state;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
