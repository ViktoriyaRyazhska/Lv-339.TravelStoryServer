package com.travelstory.entity;

import lombok.*;
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
//@EntityListeners(AuditingEntityListener.class)
@Table(name = "users")
@Component
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;

    private String lastName;

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

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "connectedUsers")
    private List<Chat> connectedChats;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private List<Message> messages;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "connectedUsers")
    private List<Chat> chats;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "userOwner")
    private List<TravelStory> travelStories;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private List<Like> likes;

    @Enumerated(EnumType.STRING)
    private Gender gender;

}
