package com.travelstory.repositories;

import com.travelstory.entity.SocialNetwork;
import com.travelstory.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SocialNetworkRepository extends JpaRepository<SocialNetwork, Long> {
    public List<SocialNetwork> getAllByUser(User user);
}
