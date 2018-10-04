package com.travelstory.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"travelStory"})
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer"})
@Table(name = "likes")

public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean likeState;

    @ManyToOne
    @JsonManagedReference
    private Media media;

    @ManyToOne
    @JsonManagedReference
    private TravelStory travelStory;


    @ManyToOne
    @JsonBackReference
    private User user;

    @CreatedDate
    private LocalDateTime createdAt;

    public void setLikeState(boolean likeState) {
        this.likeState = likeState;
    }

    public boolean isLikeState() {
        return likeState;
    }
}
