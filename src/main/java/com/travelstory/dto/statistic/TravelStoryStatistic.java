package com.travelstory.dto.statistic;

import com.travelstory.entity.TravelStory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface TravelStoryStatistic extends JpaRepository<TravelStory, Long> {

    @Query(value = "SELECT count(id) from travel_story where MONTH (created_date) =:num", nativeQuery = true)
    Long countTravelStoriesByCreatedAtMonth(@Param("num") Integer numberOfMonth);

    Integer countTravelStoriesByTravelStoryStatusEquals(TravelStory.TravelStoryStatus storyStatus);

    Integer countTravelStoriesByCreatedDateAfter(LocalDate lastUpdateDate);

}
