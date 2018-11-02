package com.travelstory.dto.statistic;

import com.travelstory.entity.TravelStoryStatus;
import com.travelstory.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        actualStatistic.put("todayUsers",
                statistic.userStatistic.countUsersByLastUpdateDateAfter(LocalDateTime.now().minusDays(1)));
        actualStatistic.put("todayTravelStories",
                statistic.travelStoryStatistic.countTravelStoriesByCreatedDateAfter(LocalDateTime.now().minusDays(1)));
        actualStatistic.put("activeTravelStories", statistic.travelStoryStatistic
                .countTravelStoriesByTravelStoryStatusEquals(TravelStoryStatus.STATUS_ACTIVE));
        return actualStatistic;
    }

    @RequestMapping("/activity")
    Map<String, ArrayList> getActivenessStatistic() {
        Map<String, ArrayList> activityStatistic = new LinkedHashMap<>();
        activityStatistic.put("likes", statistic.countAllLikesCreatedThisMouth());
        activityStatistic.put("comments", statistic.countAllCommentsCreatedThisMouth());
        activityStatistic.put("travelStories", statistic.countAllTravelStoriesCreatedThisYear());
        activityStatistic.put("users", statistic.countAllUsersByRegisteredThisYear());
        return activityStatistic;
    }

    @RequestMapping("/registration")
    Map<String, ArrayList> getRegistrationStatistic() {
        Map<String, ArrayList> registrationStatistic = new LinkedHashMap<>();
        registrationStatistic.put("yearRegistrationStatistic", statistic.countAllUsersByRegisteredThisYear());
        return registrationStatistic;
    }

    @RequestMapping("/users")
    Map<String, Integer> getUsersStatistic() {
        Map<String, Integer> userStatistics = new LinkedHashMap<>();
        userStatistics.put("male", statistic.userStatistic.countUsersByGender(User.Gender.MALE));
        userStatistics.put("female", statistic.userStatistic.countUsersByGender(User.Gender.FEMALE));
        userStatistics.put("activeThisWeek",
                statistic.userStatistic.countUsersByLastUpdateDateAfter(LocalDateTime.now().minusWeeks(1)));
        userStatistics.put("userAverageAge", statistic.userStatistic.countUsersAverageAge());
        userStatistics.put("older18Years", statistic.userStatistic.countUsersOlder(18));
        return userStatistics;
    }

    @RequestMapping("/travelStory")
    Map<String, ArrayList> getTravelStoryStatistic() {
        Map<String, ArrayList> travelStoryStatistic = new LinkedHashMap<>();
        travelStoryStatistic.put("yearTravelStoryStatistic", statistic.countAllTravelStoriesCreatedThisYear());
        return travelStoryStatistic;
    }
}
