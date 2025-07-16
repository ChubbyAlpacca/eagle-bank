package com.takehome.eagle.service.impl;

import com.takehome.eagle.entity.Address;
import com.takehome.eagle.entity.User;
import com.takehome.eagle.exceptions.EagleBankException;
import com.takehome.eagle.model.CreateUserRequest;
import com.takehome.eagle.model.CreateUserRequestAddress;
import com.takehome.eagle.model.UpdateUserRequest;
import com.takehome.eagle.model.UserResponse;
import com.takehome.eagle.repository.UserRepository;
import com.takehome.eagle.utilities.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplementationTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private AuthService authService;

    @InjectMocks
    private UserServiceImplementation userService;

    @Captor
    private ArgumentCaptor<User> userCaptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateUserAndReturnUserResponse() {
        // Arrange
        CreateUserRequestAddress address = new CreateUserRequestAddress()
                .line1("123 Main St")
                .line2("Apt 4B")
                .line3("Floor 2")
                .town("London")
                .county("Greater London")
                .postcode("E1 6AN");

        CreateUserRequest request = new CreateUserRequest()
                .name("John Doe")
                .email("john@example.com")
                .phoneNumber("1234567890")
                .address(address);

        when(authService.encrypt(anyString())).thenReturn("encryptedPassword");
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User u = invocation.getArgument(0);
            u.setId("1"); // Simulate JPA setting an ID
            return u;
        });

        // Act
        UserResponse response = userService.createUser(request);

        // Assert
        assertThat(response).isNotNull();
        assertThat(response.getName()).isEqualTo("John Doe");
        assertThat(response.getEmail()).isEqualTo("john@example.com");
        assertThat(response.getAddress().getLine1()).isEqualTo("123 Main St");

        verify(userRepository, times(1)).save(userCaptor.capture());
        assertThat(userCaptor.getValue().getAddress().getTown()).isEqualTo("London");
    }

    @Test
    void shouldThrowEagleBankExceptionOnSaveFailure() {
        // Arrange
        CreateUserRequest request = new CreateUserRequest()
                .name("John")
                .email("fail@example.com")
                .phoneNumber("000")
                .address(new CreateUserRequestAddress()
                        .line1("Fail")
                        .town("Failtown")
                        .county("Failshire")
                        .postcode("000"));
        when(authService.encrypt(anyString())).thenReturn("encryptedPassword");
        when(userRepository.save(any(User.class))).thenThrow(new RuntimeException("DB error"));

        // Act + Assert
        assertThatThrownBy(() -> userService.createUser(request))
                .isInstanceOf(EagleBankException.class)
                .hasMessage("Failed to create user");
    }

    @Test
    void shouldReturnUserResponseByUserId() {
        // Arrange
        UUID userId = UUID.randomUUID();
        Address address = Address.builder()
                .line1("123 Main St")
                .town("London")
                .county("GL")
                .postcode("E1 6AN")
                .build();

        User user = User.builder()
                .id("1")
                .userId(userId)
                .name("Jane Doe")
                .email("jane@example.com")
                .phoneNumber("9876543210")
                .address(address)
                .createdAt(LocalDateTime.now())
                .build();

        when(userRepository.getUserByUserId(userId)).thenReturn(Optional.of(user));

        // Act
        UserResponse response = userService.getuserById(userId.toString());

        // Assert
        assertThat(response).isNotNull();
        assertThat(response.getName()).isEqualTo("Jane Doe");
        assertThat(response.getAddress().getTown()).isEqualTo("London");

        verify(userRepository).getUserByUserId(userId);
    }

    @Test
    void shouldUpdateUser() {
    // Arrange
        UUID userId = UUID.randomUUID();
        User existingUser = new User();
        existingUser.setId(String.valueOf(5));
        existingUser.setUserId(userId);
        existingUser.setName("Old Name");
        existingUser.setEmail("email@email.com");
        Address address = new Address();
        address.setLine1("123 Old St");
        address.setTown("Old Town");
        address.setCounty("Old County");
        address.setPostcode("OLD123");
        existingUser.setAddress(address);

        when(userRepository.getUserByUserId(userId)).thenReturn(Optional.of(existingUser));
        UpdateUserRequest updateRequest = new UpdateUserRequest();
        updateRequest.setName("New Name");
        updateRequest.setEmail("newEmail@email.com");
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User updatedUser = invocation.getArgument(0);
            updatedUser.setId(String.valueOf(5)); // Simulate JPA setting an ID
            return updatedUser;
        });
        // Act
        UserResponse updatedResponse = userService.updateUser(userId.toString(), updateRequest);
        // Assert
        assertThat(updatedResponse).isNotNull();
        assertThat(updatedResponse.getName()).isEqualTo("New Name");
        assertThat(updatedResponse.getEmail()).isEqualTo("newEmail@email.com");
        verify(userRepository).getUserByUserId(userId);
        verify(userRepository).save(userCaptor.capture());
    }
}
