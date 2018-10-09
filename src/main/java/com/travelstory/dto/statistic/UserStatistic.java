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

    @Query(value = "SELECT AVG(DATEDIFF(YEAR(NOW()), YEAR(date_of_birth))) FROM users;", nativeQuery = true)
    Integer countUsersAverageAge();

    @Query(value = "select count(e.id) from User e where month(e.registrationDate) = ?1")
    Long countUsersRegisteredAt(Integer numberOfMonth);

    Long countUsersByGender(User.Gender gender);

//    @Query(value = "SELECT count(id) from users where last_update_date = current_date", nativeQuery = true)
    Long countUsersByUserState(User.UserState userState);

    @Query(value = "SELECT count(id) from users where year(now())-year(date_of_birth)>=:age", nativeQuery = true)
    Long countUsersOlder(@Param("age") Integer age);

}
