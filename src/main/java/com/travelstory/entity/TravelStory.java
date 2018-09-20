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

    private enum TravelStoryStatus {
        STATUS_ACTIVE, STATUS_INACTIVE;
    }

}
