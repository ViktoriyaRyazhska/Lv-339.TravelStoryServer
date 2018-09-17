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

    public Like() {
    }

    public Like(TravelStory travelStory, User user, LocalDateTime createdAt) {
        this.travelStory = travelStory;
        this.user = user;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
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
