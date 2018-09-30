package com.travelstory.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "social_networks")
public class SocialNetworkNick {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private SocialNetworks socialNetwork;

    @NotBlank
    private String nickName;

    @ManyToOne
    // @JsonManagedReference
    private User user;

    public enum SocialNetworks {
        FACEBOOK, INSTAGRAM, TWITTER
    }
}
