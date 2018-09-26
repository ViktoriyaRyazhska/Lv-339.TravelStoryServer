package com.travelstory.dao.statistic;

import com.travelstory.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserStatistic extends JpaRepository<User, Long> {

}
