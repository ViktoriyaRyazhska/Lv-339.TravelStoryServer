package com.travelstory.entity;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String commentMassage;

    @NotBlank
    @ManyToOne
    private TravelStory travelStory;

    @NotNull
    @ManyToOne
    private User user;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

}
