package com.travelstory.dto.messenger;

import com.travelstory.entity.SocialNetwork;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SocialNetworksDTO {
    private Long id;
    private SocialNetwork.SocialNetworkNames socialNetworkName;
    private String urlToHomePage;
    private String nickName;
}
