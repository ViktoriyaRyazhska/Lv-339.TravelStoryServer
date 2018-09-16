package com.travelStory.travel_story.entity;

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

    public Comment() {
    }

    public Comment(@NotBlank String commentMassage, TravelStory travelStory, User user, LocalDateTime createdAt) {
        this.commentMassage = commentMassage;
        this.travelStory = travelStory;
        this.user = user;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getCommentMassage() {
        return commentMassage;
    }

    public TravelStory getTravelStory() {
        return travelStory;
    }

    public User getUser() {
        return user;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCommentMassage(String commentMassage) {
        this.commentMassage = commentMassage;
    }

    public void setTravelStory(TravelStory travelStory) {
        this.travelStory = travelStory;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
