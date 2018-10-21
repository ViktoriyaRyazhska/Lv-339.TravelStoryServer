package com.travelstory.dto.statistic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    ArrayList<Long> countAllUsersByRegisteredThisYear() {
        ArrayList<Long> list = new ArrayList<>();
        for (int i = 1; i < 13; i++) {
            list.add(userStatistic.countUsersRegisteredAt(i));
        }
        return list;
    }

    ArrayList<Integer> countAllTravelStoriesCreatedThisYear() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i < 13; i++) {
            LocalDate begin = LocalDate.of(2018, i, 1);
            LocalDate end = LocalDate.of(2018, i, i == 2 ? 28 : 30);
            list.add(travelStoryStatistic.countTravelStoriesCreatedDateBeetween(begin, end));
        }
        return list;
    }

    ArrayList<Long> countAllCommentsCreatedThisMouth() {
        ArrayList<Long> list = new ArrayList<>();
        for (int i = 1; i < 13; i++) {
            list.add(commentStatistic.countCommentsByCreatedAtBetween(LocalDateTime.of(2018, 1, 1, 0, 0),
                    LocalDateTime.of(2018, 12, 28, 0, 0)));
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

}