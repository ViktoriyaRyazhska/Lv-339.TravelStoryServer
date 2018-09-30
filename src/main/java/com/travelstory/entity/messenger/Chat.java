package com.travelstory.entity;

import lombok.extern.slf4j.Slf4j;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "chats")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<User> connectedUsers;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "chat")
    private List<Message> messages;

    @NotBlank
    private String chatName;

    @NotNull
    private ChatType chatType;

    public enum ChatType {
        PRIVATE_MESSAGES, PRIVATE_GROUP, PUBLIC_GROUP
    }

    public Chat(List<User> connectedUsers) {
        this.connectedUsers = connectedUsers;
    }
}
