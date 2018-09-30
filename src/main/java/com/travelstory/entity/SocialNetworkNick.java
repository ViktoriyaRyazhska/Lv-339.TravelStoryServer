package com.travelstory.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "social_networks")
public class SocialNetworksNick {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private SocialNetworks socialNetwork;

    private String nickName;

    @Email
    private String email;

    @Size(min = 1, max = 25)
    private String password;

    private LocalDate dateOfBirth;

    public enum SocialNetworks{
        FACEBOOK,
        INSTAGRAM,
        TWITTER
    }
}
