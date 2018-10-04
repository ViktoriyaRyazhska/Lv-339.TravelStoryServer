package com.travelstory.dto.statistic;

import com.travelstory.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStatistic extends JpaRepository<User, Long> {
    @Query(value = "SELECT COUNT(id) FROM users", nativeQuery = true)
    Long countUsers();

    @Query(value = "SELECT count(id) FROM users where last_update_date between subdate(curdate(), 1)and curdate();", nativeQuery = true)
    Long countUsersActiveLastDay();

    @Query(value = "SELECT AVG(TIMESTAMPDIFF(YEAR, date_of_birth, CURDATE())) AS `Average` "
            + "FROM users WHERE date_of_birth IS NOT NULL;", nativeQuery = true)
    Integer countUsersAverageAge();

    @Query(value = "SELECT count(id) from users where MONTH (registration_date) =:num", nativeQuery = true)
    Long countUsersRegisteredAt(@Param("num") Integer numberOfMonth);

    @Query(value = "SELECT count(id) from users where gender = :num", nativeQuery = true)
    Long countUsersByGender(@Param("num") User.Gender gender);

    @Query(value = "SELECT count(id) from users where last_update_date = current_date", nativeQuery = true)
    Long countTodayActiveUsers();

    @Query(value = "SELECT count(id) from users where year(now())-year(date_of_birth)>18", nativeQuery = true)
    Long countOldUsers();

}
