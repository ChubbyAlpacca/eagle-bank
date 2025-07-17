package com.takehome.eagle.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.takehome.eagle.exceptions.GlobalExceptionHandler;
import com.takehome.eagle.model.BankAccountResponse;
import com.takehome.eagle.model.CreateBankAccountRequest;
import com.takehome.eagle.service.AccountService;
import com.takehome.eagle.utilities.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class AccountControllerTest {

    @Mock
    private AuthService authService;

    @Mock
    private AccountService accountService;

    @InjectMocks
    private AccountController accountController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(accountController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void createAccount_Success() throws Exception {
        // Given
        String userName = "Cam McGifford";
        CreateBankAccountRequest request = new CreateBankAccountRequest();
        request.setName(userName);
        request.setAccountType(CreateBankAccountRequest.AccountTypeEnum.PERSONAL);

        BankAccountResponse expectedResponse = new BankAccountResponse();
        expectedResponse.setAccountNumber("0112345");
        expectedResponse.setName(userName);
        expectedResponse.setAccountType(BankAccountResponse.AccountTypeEnum.PERSONAL);

        when(authService.getAuthenticatedUsernameFromContext()).thenReturn(userName);
        when(accountService.createBankAccount(any(CreateBankAccountRequest.class))).thenReturn(expectedResponse);

        // When & Then
        mockMvc.perform(post("/v1/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.accountNumber").value("0112345"))
                .andExpect(jsonPath("$.name").value(userName))
                .andExpect(jsonPath("$.accountType").value(CreateBankAccountRequest.AccountTypeEnum.PERSONAL.getValue()));

        verify(authService, times(2)).getAuthenticatedUsernameFromContext();
        verify(accountService).createBankAccount(any(CreateBankAccountRequest.class));
    }

    @Test
    void createAccount_AuthenticationFailed_DifferentUser() throws Exception {
        // Given
        String requestUserName = "Cam McGifford";
        String authenticatedUser = "Different User";

        CreateBankAccountRequest request = new CreateBankAccountRequest();
        request.setName(requestUserName);
        request.setAccountType(CreateBankAccountRequest.AccountTypeEnum.PERSONAL);

        when(authService.getAuthenticatedUsernameFromContext()).thenReturn(authenticatedUser);

        // When & Then
        mockMvc.perform(post("/v1/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isForbidden());

        verify(authService, times(2)).getAuthenticatedUsernameFromContext();
        verify(accountService, never()).createBankAccount(any(CreateBankAccountRequest.class));
    }

    @Test
    void createAccount_InvalidRequest_NullName() throws Exception {
        // Given
        CreateBankAccountRequest request = new CreateBankAccountRequest();
        request.setName(null); // Null name
        request.setAccountType(CreateBankAccountRequest.AccountTypeEnum.PERSONAL);

        // When & Then
        mockMvc.perform(post("/v1/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());

        verify(authService, never()).getAuthenticatedUsernameFromContext();
        verify(accountService, never()).createBankAccount(any(CreateBankAccountRequest.class));
    }

    @Test
    void createAccount_InvalidRequest_NullAccountType() throws Exception {
        // Given
        CreateBankAccountRequest request = new CreateBankAccountRequest();
        request.setName("Cam McGifford");
        request.setAccountType(null); // Null account type

        // When & Then
        mockMvc.perform(post("/v1/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());

        verify(authService, never()).getAuthenticatedUsernameFromContext();
        verify(accountService, never()).createBankAccount(any(CreateBankAccountRequest.class));
    }

    @Test
    void createAccount_InvalidJson() throws Exception {
        // Given
        String invalidJson = "{ invalid json }";

        // When & Then
        mockMvc.perform(post("/v1/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andExpect(status().isBadRequest());

        verify(authService, never()).getAuthenticatedUsernameFromContext();
        verify(accountService, never()).createBankAccount(any(CreateBankAccountRequest.class));
    }
}