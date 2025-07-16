package com.takehome.eagle.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.takehome.eagle.entity.Address;
import com.takehome.eagle.entity.User;
import com.takehome.eagle.exceptions.EagleBankException;
import com.takehome.eagle.exceptions.GlobalExceptionHandler;
import com.takehome.eagle.model.CreateUserRequest;
import com.takehome.eagle.model.CreateUserRequestAddress;
import com.takehome.eagle.model.UpdateUserRequest;
import com.takehome.eagle.model.UserResponse;
import com.takehome.eagle.service.UserService;
import com.takehome.eagle.service.impl.UserServiceImplementation;
import com.takehome.eagle.utilities.EncryptionService;
import com.takehome.eagle.utilities.UserValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private UserValidator userValidator;

    @Mock
    private EncryptionService encryptionService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    // Helper methods for creating test objects
    private CreateUserRequest createValidUserRequest() {
        CreateUserRequest request = new CreateUserRequest();
        request.setName("John Doe");
        request.setPassword("password123");
        request.setPhoneNumber("1234567890");
        request.setEmail("john.doe@example.com");
        request.setAddress(createValidAddress());
        return request;
    }

    private CreateUserRequestAddress createValidAddress() {
        CreateUserRequestAddress address = new CreateUserRequestAddress();
        address.setLine1("123 Main Street");
        address.setLine2("Apartment 4B");
        address.setLine3("");
        address.setTown("London");
        address.setCounty("Greater London");
        address.setPostcode("SW1A 1AA");
        return address;
    }

    private UserResponse createUserResponse(String id, String name, String email) {
        UserResponse response = new UserResponse();
        response.setId(id);
        response.setName(name);
        response.setEmail(email);
        response.setAddress(createValidAddress());
        return response;
    }

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .setValidator(new LocalValidatorFactoryBean())
                .build();
    }

    @Test
    void createUser_Success() throws Exception {
        // Given
        CreateUserRequest createUserRequest = createValidUserRequest();

        UserResponse expectedResponse = createUserResponse("usr-123456", "John Doe", "john.doe@example.com");

        // Mock behavior
        doNothing().when(userValidator).validateCreateUserRequest(any(CreateUserRequest.class));
        when(userService.createUser(any(CreateUserRequest.class))).thenReturn(expectedResponse);

        // When & Then
        mockMvc.perform(post("/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createUserRequest)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value("usr-123456"))
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"));

        // Verify interactions
        verify(userValidator).validateCreateUserRequest(any(CreateUserRequest.class));
        verify(userService).createUser(any(CreateUserRequest.class));
    }

    @Test
    void fetchUserByID_Success() throws Exception {
        // Given
        String userId = "usr-123456";
        UserResponse expectedResponse = createUserResponse(userId, "John Doe", "john.doe@example.com");

        when(userService.getuserById(eq(userId))).thenReturn(expectedResponse);

        // When & Then
        mockMvc.perform(get("/v1/users/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(userId))
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"));

        verify(userService).getuserById(eq(userId));
    }

    @Test
    void fetchUserByID_UserNotFound() throws Exception {
        // Given
        String userId = "usr-nonexistent";
        when(userService.getuserById(eq(userId)))
                .thenThrow(new EagleBankException("User not found", HttpStatusCode.valueOf(404)));

        // When & Then
        var response = mockMvc.perform(get("/v1/users/{userId}", userId))
                .andExpect(status().isNotFound())
                .andReturn();
        System.out.println(response);

        verify(userService).getuserById(eq(userId));
    }

    @Test
    void fetchUserByID_EmptyUserId() throws Exception {
        // When & Then
        mockMvc.perform(get("/v1/users/"))
                .andExpect(status().isNotFound()); // Spring returns 404 for missing path variable

        verify(userService, never()).getuserById(any());
    }

    @Test
    void fetchUserByID_UserIdWithSpecialCharacters() throws Exception {
        // Given
        String userId = "usr-ABC123def";
        UserResponse expectedResponse = createUserResponse(userId, "Jane Smith", "jane.smith@example.com");

        when(userService.getuserById(eq(userId))).thenReturn(expectedResponse);

        // When & Then
        mockMvc.perform(get("/v1/users/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(userId))
                .andExpect(jsonPath("$.name").value("Jane Smith"))
                .andExpect(jsonPath("$.email").value("jane.smith@example.com"));

        verify(userService).getuserById(eq(userId));
    }

    @Test
    void createUser_NullRequest() throws Exception {
        // When & Then
        mockMvc.perform(post("/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isBadRequest());

        verify(userValidator, never()).validateCreateUserRequest(any());
        verify(userService, never()).createUser(any());
    }

    @Test
    void createUser_MalformedJson() throws Exception {
        // When & Then
        mockMvc.perform(post("/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ invalid json }"))
                .andExpect(status().isBadRequest());

        verify(userValidator, never()).validateCreateUserRequest(any());
        verify(userService, never()).createUser(any());
    }

    @Test
    void updateUserByID_Success() throws Exception {
        String userId = "usr-123ABC";
        UpdateUserRequest request = new UpdateUserRequest();
        request.setName("Updated Name");
        request.setEmail("updated.email@example.com");
        // set other fields if any

        UserResponse updatedResponse = new UserResponse();
        updatedResponse.setId(userId);
        updatedResponse.setName("Updated Name");
        updatedResponse.setEmail("updated.email@example.com");
        // set other fields if any

        when(userService.updateUser(eq(userId), any(UpdateUserRequest.class))).thenReturn(updatedResponse);

        mockMvc.perform(patch("/v1/users/{userId}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(userId))
                .andExpect(jsonPath("$.name").value("Updated Name"))
                .andExpect(jsonPath("$.email").value("updated.email@example.com"));

        verify(userService).updateUser(eq(userId), any(UpdateUserRequest.class));
    }
}