package com.takehome.eagle.service;

import com.takehome.eagle.model.BankAccountResponse;
import com.takehome.eagle.model.CreateBankAccountRequest;

public interface AccountService {

    BankAccountResponse createBankAccount(CreateBankAccountRequest request);

}
