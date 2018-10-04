package com.travelstory.entity.messenger;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.travelstory.entity.Media;
import com.travelstory.entity.MessageType;
import com.travelstory.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@ToString(exclude = { "chat", "user", "media" })
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "messages")
@Slf4j
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String messageContent;

    @NotNull
    @ManyToOne
    @JsonBackReference
    private Chat chat;

    @NotNull
    @ManyToOne
    @JsonManagedReference
    private User user;

    @NotNull
    @Enumerated(EnumType.STRING)
    private MessageType messageType;

    @OneToOne
    private Media media;

    @NotNull
    private LocalDateTime createdAt;

    private LocalDateTime lastEditedAt;

    private LocalDateTime deletedAt;

    private boolean deleted;
}
