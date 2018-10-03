package com.travelstory.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.travelstory.entity.messenger.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "medias")
@ToString(exclude = { "likes", "comments" })
@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer" })
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @URL
    private String url;

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "media")
    private List<Like> likes;

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "media")
    private List<Comment> comments;

    @OneToOne(mappedBy = "media")
    private Message message;
}
