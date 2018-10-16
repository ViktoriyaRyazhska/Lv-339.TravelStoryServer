package com.travelstory.dto.statistic;

import com.travelstory.entity.TravelStory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface TravelStoryStatistic extends JpaRepository<TravelStory, Long> {

    Integer countTravelStoriesByCreatedDateBetween(LocalDate begin, LocalDate end);

    Integer countTravelStoriesByTravelStoryStatusEquals(TravelStory.TravelStoryStatus storyStatus);

    Integer countTravelStoriesByCreatedDateAfter(LocalDate lastUpdateDate);

}
