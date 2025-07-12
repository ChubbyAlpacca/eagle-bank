package com.takehome.eagle.repository;

import com.takehome.eagle.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.repository.config.BootstrapMode;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(bootstrapMode = BootstrapMode.LAZY)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void getUserByUserId() {
        // Given a user ID that does not exist
        UUID nonExistentUserId = UUID.randomUUID();

        // When trying to retrieve the user by the non-existent ID
        var result = userRepository.getUserByUserId(nonExistentUserId);

        // Then the result should be empty
        assertTrue(result.isEmpty(), "Expected no user to be found for the non-existent ID");
    }

    @Test
    void shouldSaveAndRetrieveUser() {
        // Given a new user
        UUID userId = UUID.randomUUID();
        User user = User.builder()
                .userId(userId)
                .name("Test User")
                .email("email@aol.com")
                .phoneNumber("1234567890")
                .build();

        assertNotNull(userRepository.save(user));
        assert (userRepository.getUserByUserId(userId).isPresent());
    }
}