package com.travelstory.repositories.statistic;

import com.travelstory.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStatistic extends JpaRepository<User, Long> {
    @Query(value = "SELECT COUNT(id) FROM users", nativeQuery = true)
    Long countUsers();

    @Query(value = "SELECT count(id) FROM users where last_update_date between subdate(curdate(), 1)and curdate();", nativeQuery = true)
    Long countUsersActiveLastDay();


    @Query(value = "SELECT AVG(TIMESTAMPDIFF(YEAR, date_of_birth, CURDATE())) AS `Average` FROM users WHERE date_of_birth IS NOT NULL;", nativeQuery = true)
    Integer countUsersAverageAge();


}
