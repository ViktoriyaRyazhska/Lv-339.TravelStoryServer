package com.travelstory.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TravelStory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String head;
    @NotBlank
    private String description;

    private LocalDate createdDate;

    private LocalDate updatedDate;

    @Enumerated(EnumType.STRING)
    private TravelStoryStatus travelStoryStatus;

    @ManyToOne
    private User userOwner;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "travelStory")
    private List<Comment> comments;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "travelStory")
    private List<Like> likes;


    public TravelStory(User userOwner, String description, LocalDate createdDate, LocalDate updatedDate,
                       TravelStoryStatus travelStoryStatus) {
        this.userOwner = userOwner;
        this.description = description;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.travelStoryStatus = travelStoryStatus;
    }


    private enum TravelStoryStatus {
        STATUS_ACTIVE, STATUS_INACTIVE;
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

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }


}
