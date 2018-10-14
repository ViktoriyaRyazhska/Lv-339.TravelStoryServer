package com.travelstory.repositories;

import com.sun.scenario.effect.Offset;
import com.travelstory.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryLookupStrategy;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    boolean existsByEmailAndPassword(String email, String password);

    User findByEmail(String email);

    List<User> findByFirstNameAndLastName(String firstName, String lastName);

    List<User> findByFirstNameIsStartingWith(String firstName);

    List<User> findByLastNameIsStartingWith(String lastName);

    List<User> findByFirstNameIsStartingWithAndLastNameIsStartingWith(String firstName, String lastName);

    List<User> findByFirstNameIsStartingWithAndLastNameIsStartingWith(String firstName, String lastName, Offset offset);

}
