package com.travelstory.repositories.statistic;

import com.travelstory.entity.TravelStory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelStoryStatistic extends JpaRepository<TravelStory, Long> {
    @Query(value = "SELECT COUNT(id) FROM travel_story", nativeQuery = true)
    Long countTravelStories();
}
