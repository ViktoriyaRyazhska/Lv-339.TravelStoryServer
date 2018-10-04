package com.travelstory.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String commentMassage;

    @ManyToOne
    @JsonManagedReference
    private Media media;

    @ManyToOne
    @JsonManagedReference
    private TravelStory travelStory;


    @ManyToOne
    private User user;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    public void setMedia(Media media) {
        this.media = media;
    }

    public void setTravelStory(TravelStory travelStory) {
        this.travelStory = travelStory;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
