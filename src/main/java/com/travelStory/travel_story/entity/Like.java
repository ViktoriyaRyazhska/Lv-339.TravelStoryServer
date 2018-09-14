package com.travelStory.travel_story.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

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

    public Like() {
    }

    public Like(TravelStory travelStory, User user) {
        this.travelStory = travelStory;
        this.user = user;
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setTravelStory(TravelStory travelStory) {
        this.travelStory = travelStory;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
