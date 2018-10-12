package com.travelstory.dto.statistic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;

@Controller
public class StatisticDTO {
    @Autowired
    UserStatistic userStatistic;
    @Autowired
    TravelStoryStatistic travelStoryStatistic;
    @Autowired
    CommentStatistic commentStatistic;
    @Autowired
    LikeStatistic likeStatistic;

    ArrayList<Long> countAllUsersByRegisteredThisMouth() {
        ArrayList<Long> list = new ArrayList<>();
        for (int i = 1; i < 13; i++) {
            list.add(userStatistic.countUsersRegisteredAt(i));
        }
        return list;
    }

    ArrayList<Long> countAllTravelStoriesCreatedByMonth() {
        ArrayList<Long> list = new ArrayList<>();
        for (int i = 1; i < 13; i++) {
            list.add(travelStoryStatistic.countTravelStoriesByCreatedAtMonth(i));
        }
        return list;
    }

    ArrayList<Long> countAllCommentsCreatedThisMouth() {
        ArrayList<Long> list = new ArrayList<>();
        for (int i = 1; i < 13; i++) {
            list.add(commentStatistic.countCommentsByCreatedAt(i));
        }
        return list;
    }

    ArrayList<Integer> countAllLikesCreatedThisMouth() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i < 13; i++) {
            list.add(likeStatistic.countLikeByCreatedAtMonth(i));
        }
        return list;
    }

    ArrayList<Integer> getUserRegistrationData() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i < 13; i++) {
            list.add(userStatistic.countUsersRegisteredAt(i).intValue());
        }
        return list;
    }
}