package com.travelStory.travel_story.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<User> connectedUsers;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Message> messages;

    @NotBlank
    private String chatName;


}
