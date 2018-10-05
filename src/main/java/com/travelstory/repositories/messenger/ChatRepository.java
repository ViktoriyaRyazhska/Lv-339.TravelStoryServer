package com.travelstory.repositories.messenger;

import com.travelstory.entity.User;
import com.travelstory.entity.messenger.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
    public List<Chat> findByConnectedUsers(User user);
}
