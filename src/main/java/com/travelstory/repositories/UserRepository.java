package com.travelstory.repositories;

import com.travelstory.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    boolean existsByEmailAndPassword(String email, String password);

    User findByEmail(String email);

    List<User> getAllBy();
}
