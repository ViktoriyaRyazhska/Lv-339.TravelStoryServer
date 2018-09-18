package com.travelstory.entity;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "likes")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @ManyToOne
    private TravelStory travelStory;

    @NotBlank
    @ManyToOne
    private User user;

    @CreatedDate
    private LocalDateTime createdAt;

}
