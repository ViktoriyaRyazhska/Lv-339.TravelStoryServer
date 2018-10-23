package com.travelstory.repositories;

import com.travelstory.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public boolean existsByEmail(String email);

    public boolean existsByEmailAndPassword(String email, String password);

    public User findByEmail(String email);

    public User findUserById(Long id);

    public List<User> getAllBy();

    public Page<User> findByFirstNameIsStartingWith(String firstName, Pageable pageable);

    public Page<User> findByLastNameIsStartingWith(String lastName, Pageable pageable);

    public Page<User> findByFirstNameIsStartingWithAndLastNameIsStartingWith(String firstName, String lastName,
                                                                             Pageable pageable);
}
