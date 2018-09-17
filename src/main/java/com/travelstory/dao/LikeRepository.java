package com.travelstory.dao;

import com.travelstory.entity.Like;
import com.travelstory.entity.Like;
import com.travelstory.entity.TravelStory;
import com.travelstory.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    /**
     * @param id
     * @return
     */
    public List<Like> findAllByTravelStoryId(Long id);

    /**
     * @param travelStory
     * @return List<Like>
     */
    public List<Like> findAllByTravelStory(TravelStory travelStory);

    /**
     * @param user
     * @return List<Like>
     */
    public List<Like> findAllByUser(User user);

    /**
     * @param id
     * @return List<Like>
     */
    public List<Like> findAllByUserId(Long id);

    /**
     * @param user
     * @param travelStory
     * @return List<Like>
     */
    public List<Like> findAllByUserAndTravelStory(User user, TravelStory travelStory);

    /**
     * @param userId
     * @param travelStoryId
     * @return List<Like>
     */
    public List<Like> findAllByUserIdAndTravelStoryId(Long userId, Long travelStoryId);

}
