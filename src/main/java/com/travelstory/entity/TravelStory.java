package com.travelstory.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Component
@ToString(exclude = { "comments", "likes", "userOwner" })
@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer" })
public class TravelStory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @NotBlank
    private String head;
    @NotBlank
    private String description;
    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime updatedDate;

    @Enumerated(EnumType.STRING)
    private TravelStoryStatus travelStoryStatus;

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "travelStory")
    private List<Like> likes;

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "travelStory")
    private List<Comment> comments;

    public TravelStory(User userOwner, String description, LocalDateTime createdDate, LocalDateTime updatedDate,
            TravelStoryStatus travelStoryStatus) {
        this.userOwner = userOwner;
        this.description = description;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.travelStoryStatus = travelStoryStatus;
    }

    @ManyToOne
    @JsonBackReference
    @NotBlank
    private User userOwner;

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
