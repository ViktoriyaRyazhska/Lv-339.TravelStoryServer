package com.travelstory.repositories.messenger;

import com.travelstory.entity.messenger.Chat;
import com.travelstory.entity.messenger.Message;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    public Message findTopByChatOrderByCreatedAtDesc(Chat chat);

    public List<Message> findAllByChatOrderByCreatedAtDesc(Chat chat, Pageable pageable);
}
