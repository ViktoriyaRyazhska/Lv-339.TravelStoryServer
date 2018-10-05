package com.travelstory.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "social_networks")
public class SocialNetwork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private SocialNetworkNames socialNetworkName;

    @NotBlank
    private String nickName;

    @NotBlank
    @URL
    private String urlToHomePage;

    @ManyToOne
    private User user;

    public enum SocialNetworkNames {
        FACEBOOK, INSTAGRAM, TWITTER
    }
}
