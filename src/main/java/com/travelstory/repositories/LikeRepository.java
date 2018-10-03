package com.travelstory.repositories;

import com.travelstory.entity.Like;
import com.travelstory.entity.Media;
import com.travelstory.entity.TravelStory;
import com.travelstory.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    /**
     * @param id
     * @return List<Like>
     */
    public List<Like> findAllByTravelStoryIdOrderByCreatedAt(Long id);

    /**
     * @param travelStory
     * @return List<Like>
     */
    public List<Like> findAllByTravelStory(TravelStory travelStory);

    /**
     * @param media
     * @return List<Like>
     */
    public List<Like> findAllByMedia(Media media);

    /**
     * @param user
     * @return List<Like>
     */
    public List<Like> findAllByUser(User user);

    /**
     * @param user
     * @param media
     * @return List<Like>
     */
    public List<Like> findAllByUserAndMedia(User user, Media media);

    /**
     * @param user
     * @param travelStory
     * @return List<Like>
     */
    public List<Like> findAllByUserAndTravelStory(User user, TravelStory travelStory);

}
