package com.travelstory.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comments")
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String commentMassage;

    @NotNull
    @ManyToOne
    private Content content;

    @NotNull
    @ManyToOne
    private User user;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

}
