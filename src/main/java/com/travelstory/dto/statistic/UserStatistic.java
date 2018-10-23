package com.travelstory.dto.statistic;

import com.travelstory.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface UserStatistic extends JpaRepository<User, Long> {

    Integer countUsersByLastUpdateDateAfter(LocalDateTime lastUpdateDate);

    @Query(value = "SELECT YEAR(NOW())-AVG(YEAR(date_of_birth)) FROM users where date_of_birth is not null;", nativeQuery = true)
    Integer countUsersAverageAge();

    @Query(value = "select count(id) from users where month(registration_date)=?", nativeQuery = true)
    Long countUsersRegisteredAt(Integer numOfMonth);

    Integer countUsersByGender(User.Gender gender);

    Long countUsersByUserState(User.UserState userState);

    @Query(value = "SELECT count(id) from users where year(now())-year(date_of_birth)>=:age", nativeQuery = true)
    Integer countUsersOlder(@Param("age") Integer age);

}
