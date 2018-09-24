package com.travelstory.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
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
    private Chat chat;

    @NotNull
    @ManyToOne
    private User user;

    @NotNull
    private LocalDateTime createdAt;

    private LocalDateTime lastEditedAt;

    private LocalDateTime deletedAt;

    private boolean deleted;
}
