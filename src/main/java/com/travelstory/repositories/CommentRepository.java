package com.travelstory.repositories;

import com.travelstory.entity.Comment;
import com.travelstory.entity.Media;
import com.travelstory.entity.TravelStory;
import com.travelstory.entity.User;
import org.springframework.data.domain.Pageable;
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
     * @param travelStoryId
     * @return List<Comment>
     */
    public List<Comment> findAllByTravelStoryId(Long travelStoryId);

    /**
     * @param pageable
     * @param travelStoryId
     * @return List<Comment>
     */
    public List<Comment> findAllByTravelStoryIdOrderByCreatedAtDesc(Long travelStoryId, Pageable pageable);

    /**
     * @param mediaId
     * @return List<Comment>
     */
    public List<Comment> findAllByMediaId(Long mediaId);

    /**
     * @param pageable
     * @param mediaId
     * @return List<Comment>
     */
    public List<Comment> findAllByMediaId(Long mediaId, Pageable pageable);

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

}
