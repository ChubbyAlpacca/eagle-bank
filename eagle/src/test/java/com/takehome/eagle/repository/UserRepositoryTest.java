package com.takehome.eagle.repository;

import com.takehome.eagle.entity.Address;
import com.takehome.eagle.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(properties = "spring.liquibase.enabled=true")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void getUserByUserId_shouldReturnEmptyWhenNotFound() {
        UUID nonExistentUserId = UUID.randomUUID();
        var result = userRepository.getUserByUserId(nonExistentUserId);
        assertTrue(result.isEmpty(), "Expected no user to be found for the non-existent ID");
    }

    @Test
    void shouldSaveAndRetrieveUserWithAddress() {
        // Given
        UUID userId = UUID.randomUUID();

        User user = User.builder()
                .userId(userId)
                .name("Test User")
                .email("test@example.com")
                .phoneNumber("1234567890")
                .createdAt(LocalDateTime.now())
                .build();

        Address address = Address.builder()
                .line1("123 Main St")
                .line2("Apt 4B")
                .line3("Floor 2")
                .town("Springfield")
                .county("Example County")
                .postcode("ABC123")
                .user(user)  // Link address to user
                .build();

        user.setAddress(address); // Link user to address

        // When
        User saved = userRepository.save(user);

        // Then
        assertNotNull(saved.getId());
        assertThat(userRepository.getUserByUserId(userId)).isPresent();

        var retrieved = userRepository.getUserByUserId(userId).get();
        assertEquals("Test User", retrieved.getName());
        assertEquals("123 Main St", retrieved.getAddress().getLine1());
    }
}
