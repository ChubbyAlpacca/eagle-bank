package com.takehome.eagle.repository;

import com.takehome.eagle.entity.User;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public abstract class UserRepository implements CrudRepository<User, Long> {

    // Additional methods specific to User can be added here if needed
}
