package com.travelstory.dto.statistic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
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

    private ArrayList<LocalDate> getMarginDates(int numberOfMonth) {
        ArrayList<LocalDate> dates = new ArrayList<>();
        dates.add(LocalDate.of(LocalDate.now().getYear(), numberOfMonth, 1));
        dates.add(LocalDate.of(LocalDate.now().getYear(), numberOfMonth,
                YearMonth.of(LocalDate.now().getYear(), numberOfMonth).lengthOfMonth()));
        return dates;
    }

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
            ArrayList<LocalDate> localDates = getMarginDates(i);
            list.add(travelStoryStatistic.countTravelStoriesCreatedDateBeetween(localDates.get(0), localDates.get(1)));
        }
        return list;
    }

    ArrayList<Long> countAllCommentsCreatedThisMouth() {
        ArrayList<Long> list = new ArrayList<>();
        for (int i = 1; i < 13; i++) {
            ArrayList<LocalDate> localDates = getMarginDates(i);
            list.add(commentStatistic.countCommentsByCreatedAtBetween(
                    LocalDateTime.of(localDates.get(0), LocalTime.MIDNIGHT),
                    LocalDateTime.of(localDates.get(1), LocalTime.MIDNIGHT)));
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