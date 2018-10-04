package com.travelstory.dto.statistic;

import com.travelstory.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LikeStatistic extends JpaRepository<Like, Long> {
    @Query(value = "SELECT count(id) from likes where MONTH (created_at) =:num", nativeQuery = true)
    Long countLikeByCreatedAtMonth(@Param("num") Integer numberOfMonth);

}
