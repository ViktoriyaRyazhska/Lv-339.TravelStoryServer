package com.travelstory.dao;

import com.travelstory.entity.Comment;
import com.travelstory.entity.Content;
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
    public List<Comment> findAllByContentId(Long id);

    /**
     * @param content
     * @return List<Comment>
     */
    public List<Comment> findAllByContent(Content content);

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
     * @param content
     * @return List<Comment>
     */
    public List<Comment> findAllByUserAndContent(User user, Content content);

    /**
     * @param userId
     * @param contentId
     * @return List<Comment>
     */
    public List<Comment> findAllByUserIdAndContentId(Long userId, Long contentId);

}
