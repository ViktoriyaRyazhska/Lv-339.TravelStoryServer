package com.travelstory.entity.messenger;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.travelstory.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Data
@ToString(exclude = { "messages", "connectedUsers", "creator" })
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "chats")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<User> connectedUsers;

    @ManyToOne
    private User creator;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "chat")
    @JsonManagedReference
    private List<Message> messages;

    @NotBlank
    private String chatName;

    private String avatar;

    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ChatType chatType;

    public enum ChatType {
        PRIVATE_MESSAGES, PRIVATE_GROUP, PUBLIC_GROUP
    }

    @PastOrPresent
    private LocalDateTime createdAt;

    @PastOrPresent
    private LocalDateTime deletedAt;

    @NotNull
    private boolean deleted;
}
