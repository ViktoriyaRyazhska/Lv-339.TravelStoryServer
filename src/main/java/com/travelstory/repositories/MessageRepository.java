package com.travelstory.repositories;

import com.travelstory.entity.messenger.Chat;
import com.travelstory.entity.messenger.Message;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    // public List<Message> findAllByChat(@Param("chat") Chat chat, Pageable pageRequest);

    // public List<Message> findAll(Pageable pageable);
    public Message findTopByChatOrderByCreatedAt(Chat chat);

    public List<Message> findAllByChatOrderByCreatedAt(Chat chat, Pageable pageable);
}
