package com.takehome.eagle.repository;

import com.takehome.eagle.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> getUserByUserId(String userId);

    Optional<User> getUserByName(String userName);
}
