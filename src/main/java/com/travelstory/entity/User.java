package com.travelstory.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.travelstory.entity.messenger.Chat;
import com.travelstory.entity.messenger.Message;
import lombok.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
// @EntityListeners(AuditingEntityListener.class)
@Table(name = "users")
@Component
public class User {

    private String firstName;

    private String lastName;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    private String email;

    @Size(min = 1, max = 60)
    private String password;

    private LocalDate dateOfBirth;

    @NotNull
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    @CreatedDate
    private LocalDateTime registrationDate;

    @LastModifiedDate
    private LocalDateTime lastUpdateDate;

    private String profilePic;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<User> following;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "following")
    private List<User> followers;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    @JsonBackReference
    private List<Media> media;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "connectedUsers")
    private List<Chat> connectedChats;

    private String backgroundPic;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private List<Message> messages;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "connectedUsers")

    private List<Chat> chats;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "creator")
    private List<Chat> createdChats;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "userOwner")
    private List<TravelStory> travelStories;

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private List<Like> likes;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private List<SocialNetwork> socialNetworks;

    private String location;

    private String bio;

    @Enumerated(EnumType.STRING)
    private UserState userState;

    public enum UserState {
        ONLINE, OFFLINE, AWAY, BUSY
    }

    public enum UserStatus {
        ACTIVE, BANNED, DELETED;
    }

    public enum Gender {
        MALE, FEMALE
    }
}
