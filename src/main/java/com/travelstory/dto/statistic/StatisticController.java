package com.travelstory.dto.statistic;

import com.travelstory.entity.TravelStory;
import com.travelstory.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/statistics")
public class StatisticController {
    @Autowired
    StatisticDTO statistic;

    @RequestMapping("/actual")
    Map<String, Integer> getActivityStatistic() {
        Map<String, Integer> actualStatistic = new LinkedHashMap<>();
        actualStatistic.put("users", (int) statistic.userStatistic.count());
        actualStatistic.put("today users",
                statistic.userStatistic.countUsersByLastUpdateDateAfter(LocalDateTime.now().minusDays(1)));
        actualStatistic.put("today travelStories",
                statistic.travelStoryStatistic.countTravelStoriesByCreatedDateAfter(LocalDate.now().minusDays(1)));
        actualStatistic.put("active travelStories", statistic.travelStoryStatistic
                .countTravelStoriesByTravelStoryStatusEquals(TravelStory.TravelStoryStatus.STATUS_ACTIVE));
        return actualStatistic;
    }

    @RequestMapping("/activity")
    Map<String, ArrayList> getActivenessStatistic() {
        Map<String, ArrayList> activityStatistic = new LinkedHashMap<>();
        activityStatistic.put("likes", statistic.countAllLikesCreatedThisMouth());
        activityStatistic.put("comments", statistic.countAllCommentsCreatedThisMouth());
        activityStatistic.put("travelStories", statistic.countAllTravelStoriesCreatedThisYear());
        activityStatistic.put("users", statistic.countAllUsersByRegisteredThisMouth());
        return activityStatistic;
    }

    @RequestMapping("/registration")
    Map<String, ArrayList> getRegistrationStatistic() {
        Map<String, ArrayList> registrationStatistic = new LinkedHashMap<>();
        registrationStatistic.put("year registration statistic", statistic.getUserRegistrationData());
        return registrationStatistic;
    }

    @RequestMapping("/users")
    Map<String, Integer> getUsersStatistic() {
        Map<String, Integer> userStatistics = new LinkedHashMap<>();
        userStatistics.put("male", statistic.userStatistic.countUsersByGender(User.Gender.MALE));
        userStatistics.put("female", statistic.userStatistic.countUsersByGender(User.Gender.FEMALE));
        userStatistics.put("active this week",
                statistic.userStatistic.countUsersByLastUpdateDateAfter(LocalDateTime.now().minusWeeks(1)));
        userStatistics.put("user average age", statistic.userStatistic.countUsersAverageAge());
        userStatistics.put("older 18 years", statistic.userStatistic.countUsersOlder(18));
        return userStatistics;
    }

    @RequestMapping("/travelStory")
    Map<String, ArrayList> getTravelStoryStatistic() {
        Map<String, ArrayList> travelStoryStatistic = new LinkedHashMap<>();
        travelStoryStatistic.put("year travelStory statistic", statistic.countAllTravelStoriesCreatedThisYear());
        travelStoryStatistic.put("since month", new ArrayList(
                statistic.travelStoryStatistic.countTravelStoriesByCreatedDateAfter(LocalDate.now().minusMonths(1))));
        return travelStoryStatistic;
    }
}
