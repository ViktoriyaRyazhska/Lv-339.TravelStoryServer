package com.travelstory.repositories.statistic;

import com.travelstory.entity.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/admin/statistics")
public class DashCardStatistic {
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
    ArrayList<ActivityStatistic> getActivityGraph() {
        ActivityStatistic likes = new ActivityStatistic("rgba(92, 107, 192, .7)", "rgba(92, 107, 192, .7)",
                statistic.countAllLikesCreatedThisMouth(), "Likes", false);
        ActivityStatistic comments = new ActivityStatistic("rgba(66, 165, 245, .7)", "rgba(69, 39, 160, .7)",
                statistic.countAllCommentsCreatedThisMouth(), "Comments", false);
        ActivityStatistic travelStories = new ActivityStatistic("rgba(38, 166, 154, .7)", "rgba(69, 39, 160, .7)",
                statistic.countAllTravelStoriesCreatedByMonth(), "TravelStories", false);
        ActivityStatistic users = new ActivityStatistic("rgba(102, 187, 106, .7)", "rgba(255, 99, 132)",
                statistic.countAllCommentsCreatedThisMouth(), "Users", false);
        ArrayList<ActivityStatistic> list = new ArrayList<>();
        list.add(likes);
        list.add(comments);
        list.add(travelStories);
        list.add(users);
        return list;
    }

    @RequestMapping("/registration")
    ArrayList<RegistrationStatistic> getRegistrationStatistic() {
        ArrayList<RegistrationStatistic> registrationStatistic = new ArrayList<>();
        registrationStatistic.add(new RegistrationStatistic("rgba(92, 107, 192, 0.36)", "rgba(92, 107, 192,.5)",
                "Dataset", "start", statistic.getUserRegistrationData()));
        return registrationStatistic;
    }

    @RequestMapping("/travelstory")
    ArrayList<SharePeaceStatistic> getTravelStoryStatistic() {
        ArrayList<SharePeaceStatistic> travelStoryStatistic = new ArrayList<>();
        travelStoryStatistic.add(new SharePeaceStatistic("rgba(255, 255, 255, 1)", "rgba(255, 255, 255, 1)",
                "rgba(255, 255, 255, 1)", "rgba(255, 255, 255, 1)", "rgba(255, 255, 255, 1)", "rgba(255, 255, 255, 1)",
                "rgba(220,220,220,1)", statistic.countAllLikesCreatedThisMouth()));
        return travelStoryStatistic;
    }

    @RequestMapping("/total")
    ArrayList<CardStatistic> getCardStatistic() {
        ArrayList<CardStatistic> list = new ArrayList<>();
        list.add(new CardStatistic("MALE x FEMALE", (userStatistic.countUsersByGender(Gender.MALE) / 100),
                Math.toIntExact(userStatistic.countUsers()), "#FFF968", "#B1A7FF", "#7986CB"));
        list.add(new CardStatistic("ONLINE", ((userStatistic.countTodayActiveUsers())),
                Math.toIntExact(userStatistic.countUsers()), "#FFE268", "#A7C1FF", "#42A5F5"));
        list.add(new CardStatistic("ONGOING TRAVELSTORIES", (travelStoryStatistic.countActiveTravelStories()),
                Math.toIntExact(travelStoryStatistic.countTravelStories()), "#FFC368", "#A7F0FF", "#26A69A"));
        list.add(new CardStatistic("USERS OVER 18 y.o", (userStatistic.countOldUsers()),
                Math.toIntExact((userStatistic.countUsers())), "#FFCF68", "#A7DEFF", "#26C6DA"));
        return list;
    }
}