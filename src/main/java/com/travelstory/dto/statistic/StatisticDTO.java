package com.travelstory.dto.statistic;

import com.travelstory.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/admin/statistics")
public class StatisticDTO {
    @Autowired
    private UserStatistic userStatistic;
    @Autowired
    TravelStoryStatistic travelStoryStatistic;
    @Autowired
    CommentStatistic commentStatistic;
    @Autowired
    LikeStatistic likeStatistic;
    @Autowired
    StatisticImpl statistic;

    @RequestMapping("/actual")
    ArrayList<DashCard> getDashCards() {

        DashCard users = new DashCard("#5C6BC0", "#7986CB", userStatistic.countUsers(), "USERS", "'room'");
        DashCard active = new DashCard("#42A5F5", "#64B5F6", userStatistic.countUsersActiveLastDay(), "ACTIVE",
                "'language'");
        DashCard travelStories = new DashCard("#42A5F5", "#64B5F6", travelStoryStatistic.countTravelStories(),
                "TRAVELSTORIES", "'dashboard'");
        DashCard averageAge = new DashCard("#66BB6A", "#81C784", userStatistic.countUsersAverageAge().longValue(),
                "AVERAGE AGE", "'account_circle'");
        ArrayList<DashCard> list = new ArrayList<>();
        list.add(users);
        list.add(active);
        list.add(travelStories);
        list.add(averageAge);
        return list;
    }

    @RequestMapping("/activity")
    ArrayList<ActivityCard> getActivityGraph() {
        ActivityCard likes = new ActivityCard("rgba(92, 107, 192, .7)", "rgba(92, 107, 192, .7)",
                statistic.countAllLikesCreatedThisMouth(), "Likes", false);
        ActivityCard comments = new ActivityCard("rgba(66, 165, 245, .7)", "rgba(69, 39, 160, .7)",
                statistic.countAllCommentsCreatedThisMouth(), "Comments", false);
        ActivityCard travelStories = new ActivityCard("rgba(38, 166, 154, .7)", "rgba(69, 39, 160, .7)",
                statistic.countAllTravelStoriesCreatedByMonth(), "TravelStories", false);
        ActivityCard users = new ActivityCard("rgba(102, 187, 106, .7)", "rgba(255, 99, 132)",
                statistic.countAllCommentsCreatedThisMouth(), "Users", false);
        ArrayList<ActivityCard> list = new ArrayList<>();
        list.add(likes);
        list.add(comments);
        list.add(travelStories);
        list.add(users);
        return list;
    }

    @RequestMapping("/registration")
    ArrayList<RegistrationCard> getRegistrationStatistic() {
        ArrayList<RegistrationCard> registrationStatistic = new ArrayList<>();
        registrationStatistic.add(new RegistrationCard("rgba(92, 107, 192, 0.36)", "rgba(92, 107, 192,.5)", "Dataset",
                "start", statistic.getUserRegistrationData()));
        return registrationStatistic;
    }

    @RequestMapping("/travelstory")
    ArrayList<SharePeaceCard> getTravelStoryStatistic() {
        ArrayList<SharePeaceCard> travelStoryStatistic = new ArrayList<>();
        travelStoryStatistic.add(new SharePeaceCard("rgba(255, 255, 255, 1)", "rgba(255, 255, 255, 1)",
                "rgba(255, 255, 255, 1)", "rgba(255, 255, 255, 1)", "rgba(255, 255, 255, 1)", "rgba(255, 255, 255, 1)",
                "rgba(220,220,220,1)", statistic.countAllLikesCreatedThisMouth()));
        return travelStoryStatistic;
    }

    @RequestMapping("/total")
    ArrayList<TotalStatisticCard> getCardStatistic() {
        ArrayList<TotalStatisticCard> list = new ArrayList<>();
        list.add(new TotalStatisticCard("MALE x FEMALE", (userStatistic.countUsersByGender(User.Gender.MALE) / 100),
                Math.toIntExact((userStatistic.countUsersByGender(User.Gender.FEMALE) / 100)), "#FFF968", "#B1A7FF",
                "#7986CB"));
        list.add(new TotalStatisticCard("ONLINE", ((userStatistic.countTodayActiveUsers())),
                Math.toIntExact(userStatistic.countUsers()), "#FFE268", "#A7C1FF", "#42A5F5"));
        list.add(new TotalStatisticCard("ONGOING TRAVELSTORIES", (travelStoryStatistic.countActiveTravelStories()),
                Math.toIntExact(travelStoryStatistic.countTravelStories()), "#FFC368", "#A7F0FF", "#26A69A"));
        list.add(new TotalStatisticCard("USERS OVER 18 y.o", (userStatistic.countOldUsers()),
                Math.toIntExact((userStatistic.countUsers())), "#FFCF68", "#A7DEFF", "#26C6DA"));
        return list;
    }
}