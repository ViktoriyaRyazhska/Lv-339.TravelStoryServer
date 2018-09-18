package com.travelstory.dao;

import com.travelstory.entity.Like;
import com.travelstory.entity.Content;
import com.travelstory.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    /**
     * @param id
     * @return
     */
    public List<Like> findAllByContentId(Long id);

    /**
     * @param content
     * @return List<Like>
     */
    public List<Like> findAllByContent(Content content);

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
     * @param content
     * @return List<Like>
     */
    public List<Like> findAllByUserAndContent(User user, Content content);

    /**
     * @param userId
     * @param contentId
     * @return List<Like>
     */
    public List<Like> findAllByUserIdAndContentId(Long userId, Long contentId);

}
