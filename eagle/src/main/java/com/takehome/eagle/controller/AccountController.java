package com.takehome.eagle.controller;

import com.takehome.eagle.api.AccountApi;
import com.takehome.eagle.model.BankAccountResponse;
import com.takehome.eagle.model.CreateBankAccountRequest;
import com.takehome.eagle.model.ListBankAccountsResponse;
import com.takehome.eagle.model.UpdateBankAccountRequest;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AccountController implements AccountApi {

    @Override
    public ResponseEntity<BankAccountResponse> createAccount(
            @Parameter(name = "CreateBankAccountRequest", description = "Create a new bank account for the user", required = true) @Valid @RequestBody CreateBankAccountRequest createBankAccountRequest
    ) {
        return null;
    }

    @Override
    public ResponseEntity<ListBankAccountsResponse> listAccounts() {
        //TODO update OAS to include userId as param - task request all accts assoc with user
        return null;
    }

    @Override
    public ResponseEntity<BankAccountResponse> fetchAccountByAccountNumber(
            @Pattern(regexp = "^01\\d{6}$") @Parameter(name = "accountNumber", description = "Account number of the bank account", required = true, in = ParameterIn.PATH) @PathVariable("accountNumber") String accountNumber
    ) {
        return null;
    }

    @Override
    public  ResponseEntity<BankAccountResponse> updateAccountByAccountNumber(
            @Pattern(regexp = "^01\\d{6}$") @Parameter(name = "accountNumber", description = "Account number of the bank account", required = true, in = ParameterIn.PATH) @PathVariable("accountNumber") String accountNumber,
            @Parameter(name = "UpdateBankAccountRequest", description = "Update bank account details for the user", required = true) @Valid @RequestBody UpdateBankAccountRequest updateBankAccountRequest
    ) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteAccountByAccountNumber(
            @Pattern(regexp = "^01\\d{6}$") @Parameter(name = "accountNumber", description = "Account number of the bank account", required = true, in = ParameterIn.PATH) @PathVariable("accountNumber") String accountNumber
    ) {
        return null;
    }

}
