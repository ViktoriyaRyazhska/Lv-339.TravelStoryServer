package com.travelstory.dao;

import com.travelstory.entity.Comment;
import com.travelstory.entity.Media;
import com.travelstory.entity.TravelStory;
import com.travelstory.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    /**
     * @param id
     * @return List<Comment>
     */
    public List<Comment> findAllByTravelStoryIdOrderByCreatedAt(Long id);

    /**
     * @param travelStory
     * @return List<Comment>
     */
    public List<Comment> findAllByTravelStory(TravelStory travelStory);


    /**
     * @param media
     * @return List<Comment>
     */
    public List<Comment> findAllByMedia(Media media);

    /**
     * @param user
     * @return List<Comment>
     */
    public List<Comment> findAllByUser(User user);

    /**
     * @param user
     * @param media
     * @return List<Comment>
     */
    public List<Comment> findAllByUserAndMedia(User user, Media media);

    /**
     * @param user
     * @param travelStory
     * @return List<Comment>
     */
    public List<Comment> findAllByUserAndTravelStory(User user, TravelStory travelStory);

    /**
     * @param media
     * @param parentId
     * @return List<Comment>
     */
    List<Comment> findAllByMediaAndParentId(Media media, Long parentId);
}
