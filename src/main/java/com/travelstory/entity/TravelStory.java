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
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Component
@ToString(exclude = {"comments", "likes", "userOwner"})
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer"})
public class TravelStory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "travelStory")
    @JsonBackReference
    private List<Media> media;

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
    @NotNull
    private User userOwner;
}
