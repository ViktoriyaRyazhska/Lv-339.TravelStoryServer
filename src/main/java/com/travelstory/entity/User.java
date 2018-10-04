package com.travelstory.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.travelstory.entity.messenger.Chat;
import com.travelstory.entity.messenger.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
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

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = { "connectedChats", "messages", "travelStories", "chats", "createdChats", "socialNetworks",
        "likes" })
// @EntityListeners(AuditingEntityListener.class)
@Table(name = "users")
@Component
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    @JsonBackReference
    private List<Media> media;

    @Email
    private String email;

    @Size(min = 1, max = 25)
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

    private String backgroundPic;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "connectedUsers")
    private List<Chat> connectedChats;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    // @JsonBackReference
    private List<Message> messages;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "connectedUsers")
    // @JsonBackReference
    private List<Chat> chats;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "creator")
    private List<Chat> createdChats;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "userOwner")
    private List<TravelStory> travelStories;

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private List<Like> likes;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    @JsonManagedReference
    private List<SocialNetwork> socialNetworks;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String location;

    private String hobbies;

    @OneToMany(mappedBy = "user")
    private List<Follow> follows;

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
