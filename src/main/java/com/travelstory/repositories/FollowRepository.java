package com.travelstory.repositories;

import com.travelstory.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    List<Follow> getFollowByUserId(long id);

}
