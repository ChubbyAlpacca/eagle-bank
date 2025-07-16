package com.takehome.eagle.utilities;

import com.takehome.eagle.exceptions.EagleBankException;
import com.takehome.eagle.model.BadRequestErrorResponse;
import com.takehome.eagle.model.BadRequestErrorResponseDetailsInner;
import com.takehome.eagle.model.CreateUserRequest;
import com.takehome.eagle.model.CreateUserRequestAddress;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserValidator {

    final String FIELD_EMPTY_OR_NULL = "This is a required field and cannot be empty or null";

    public void validateCreateUserRequest(CreateUserRequest request) {
        BadRequestErrorResponse response = new BadRequestErrorResponse();
        List<BadRequestErrorResponseDetailsInner> errorDetails = new ArrayList<>();

        if (request.getName() == null || request.getName().isEmpty()) {
            errorDetails.add(buildErrorDetail("Name", "string"));
        }
        if (request.getEmail() == null || request.getEmail().isEmpty()) {
            errorDetails.add(buildErrorDetail("Email", "string"));
        }
        if (request.getPhoneNumber() == null || request.getPhoneNumber().isEmpty()) {
            errorDetails.add(buildErrorDetail("Phone number", "string"));
        }
        if (request.getPassword() == null || request.getPassword().isEmpty()) {
            errorDetails.add(buildErrorDetail("Password", "string"));
        }
        if (request.getAddress() == null) {
            errorDetails.add(buildErrorDetail("Address", "object"));
        } else {
            var addressErrors = validateAddressRequest(request.getAddress(), errorDetails);
            if (!addressErrors.isEmpty()) {
                errorDetails.addAll(addressErrors);
            }
        }
        if (!errorDetails.isEmpty()) {
            response.setDetails(errorDetails);
            throw new EagleBankException("Validation failed", HttpStatusCode.valueOf(400), response);
        }
    }

    private List<BadRequestErrorResponseDetailsInner> validateAddressRequest(CreateUserRequestAddress address, List<BadRequestErrorResponseDetailsInner> errorDetails) {
        if (address.getLine1() == null || address.getLine1().isEmpty()) {
            errorDetails.add(buildErrorDetail("Address line 1", "string"));
        }
        if (address.getTown() == null || address.getTown().isEmpty()) {
            errorDetails.add(buildErrorDetail("Address line 2", "string"));
        }
        if (address.getCounty() == null || address.getCounty().isEmpty()) {
            errorDetails.add(buildErrorDetail("County", "string"));
        }
        if (address.getPostcode() == null || address.getPostcode().isEmpty()) {
            errorDetails.add(buildErrorDetail("PostCode", "string"));
        }
        return errorDetails;
    }

    private BadRequestErrorResponseDetailsInner buildErrorDetail(String field, String type) {
        BadRequestErrorResponseDetailsInner detail = new BadRequestErrorResponseDetailsInner();
        detail.setField(field);
        detail.setMessage(FIELD_EMPTY_OR_NULL);
        detail.setType(type);
        return detail;
    }

}
