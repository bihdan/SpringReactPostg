package org.example.spring_react_postg.repository;

import org.example.spring_react_postg.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u")
    List<User> findAllUsers();

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

}
