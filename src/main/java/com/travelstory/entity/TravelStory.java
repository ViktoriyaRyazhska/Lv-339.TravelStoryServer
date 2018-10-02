package com.travelstory.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
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
    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime updatedDate;

    @Enumerated(EnumType.STRING)
    private TravelStoryStatus travelStoryStatus;

    @ManyToOne
    @JsonBackReference
    private User userOwner;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "travelStory")
    private List<Comment> comments;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "travelStory")
    private List<Like> likes;

    public TravelStory(User userOwner, String description, LocalDateTime createdDate, LocalDateTime updatedDate,
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

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
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
