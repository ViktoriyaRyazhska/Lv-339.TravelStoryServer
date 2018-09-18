package com.travelstory.dao;

import com.travelstory.entity.Comment;
import com.travelstory.entity.TravelStory;
import com.travelstory.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    /**
     *
     * @param id
     * @return
     */
    public List<Comment> findAllByTravelStoryId(Long id);

    /**
     * @param travelStory
     * @return List<Comment>
     */
    public List<Comment> findAllByTravelStory(TravelStory travelStory);

    /**
     * @param user
     * @return List<Comment>
     */
    public List<Comment> findAllByUser(User user);

    /**
     * @param id
     * @return List<Comment>
     */
    public List<Comment> findAllByUserId(Long id);

    /**
     * @param user
     * @param travelStory
     * @return List<Comment>
     */
    public List<Comment> findAllByUserAndTravelStory(User user, TravelStory travelStory);

    /**
     * @param userId
     * @param travelStoryId
     * @return List<Comment>
     */
    public List<Comment> findAllByUserIdAndTravelStoryId(Long userId, Long travelStoryId);

}
