package com.travelstory.repositories.statistic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/auth")
public class DashCardStatistic {
    @Autowired
    private UserStatistic userStatistic;
    @Autowired
    TravelStoryStatistic travelStoryStatistic;
    ArrayList<DashCard> getDashCards() {

        DashCard users = new DashCard("#5C6BC0", "#7986CB", userStatistic.countUsers(),
                "USERS", "room");
        DashCard active = new DashCard("#42A5F5", "#64B5F6", userStatistic.countUsersActiveLastDay(),
                "ACTIVE", "language");
        DashCard travelStories = new DashCard("#42A5F5", "#64B5F6", travelStoryStatistic.countTravelStories(),
                "TRAVELSTORIES", "dashboard");
        DashCard averageAge = new DashCard("#66BB6A", "#81C784", userStatistic.countUsersAverageAge().longValue(),
                "AVERAGE AGE", "account_circle");
        ArrayList<DashCard> list = new ArrayList<>();
        list.add(users);
        list.add(active);
        list.add(travelStories);
        list.add(averageAge);
        return list;
    }
}
