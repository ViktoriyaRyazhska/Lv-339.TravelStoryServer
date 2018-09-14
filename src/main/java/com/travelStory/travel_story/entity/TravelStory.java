package com.travelStory.travel_story.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class TravelStory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToMany
    private User userOwner;
    private String description;
    private LocalDate createdDate;
    private LocalDate updatedDate;
    @Enumerated(EnumType.STRING)
    private TravelStoryStatus travelStoryStatus;

    public TravelStory() {
    }

    public TravelStory(User userOwner, String description, LocalDate createdDate, LocalDate updatedDate, TravelStoryStatus travelStoryStatus) {
        this.userOwner = userOwner;
        this.description = description;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.travelStoryStatus = travelStoryStatus;
    }

    private enum TravelStoryStatus {
        STATUS_ACTIVE,
        STATUS_INECTIVE;

        TravelStoryStatus() {

        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUserOwner() {
        return userOwner;
    }

    public void setUserOwner(User userOwner) {
        this.userOwner = userOwner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public TravelStoryStatus getTravelStoryStatus() {
        return travelStoryStatus;
    }

    public void setTravelStoryStatus(TravelStoryStatus travelStoryStatus) {
        this.travelStoryStatus = travelStoryStatus;
    }
}
