package com.takehome.eagle.utilities;

import com.takehome.eagle.model.CreateUserRequest;
import com.takehome.eagle.model.CreateUserRequestAddress;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class UserValidatorTest {

    private UserValidator validator;

    @BeforeEach
    void setUp() {
        validator = new UserValidator();
    }

    @Test
    void shouldPassValidationWithValidInput() {
        CreateUserRequest request = new CreateUserRequest()
                .name("John Doe")
                .email("john@example.com")
                .phoneNumber("1234567890")
                .password("securePass123")
                .address(new CreateUserRequestAddress()
                        .line1("123 Street")
                        .town("London")
                        .county("Greater London")
                        .postcode("E1 6AN")
                );

        assertThatCode(() -> validator.validateCreateUserRequest(request))
                .doesNotThrowAnyException();
    }

    @Test
    void shouldFailWhenNameIsMissing() {
        CreateUserRequest request = validRequest();
        request.setName(null);

        assertThatThrownBy(() -> validator.validateCreateUserRequest(request))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Validation failed");
    }

    @Test
    void shouldFailWhenEmailIsEmpty() {
        CreateUserRequest request = validRequest();
        request.setEmail("");

        assertThatThrownBy(() -> validator.validateCreateUserRequest(request))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Validation failed");
    }

    @Test
    void shouldFailWhenAddressIsMissing() {
        CreateUserRequest request = validRequest();
        request.setAddress(null);

        assertThatThrownBy(() -> validator.validateCreateUserRequest(request))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Validation failed");
    }

    @Test
    void shouldFailWhenAddressFieldsAreEmpty() {
        CreateUserRequest request = validRequest();
        request.setAddress(new CreateUserRequestAddress());

        assertThatThrownBy(() -> validator.validateCreateUserRequest(request))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Validation failed");
    }

    private CreateUserRequest validRequest() {
        return new CreateUserRequest()
                .name("Jane Smith")
                .email("jane@example.com")
                .phoneNumber("9876543210")
                .password("pass123")
                .address(new CreateUserRequestAddress()
                        .line1("456 Avenue")
                        .town("Oxford")
                        .county("Oxfordshire")
                        .postcode("OX1 1AA"));
    }
}
