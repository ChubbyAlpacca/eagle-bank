package com.takehome.eagle.service.impl;

import com.takehome.eagle.entity.Accounts;
import com.takehome.eagle.model.BankAccountResponse;
import com.takehome.eagle.model.CreateBankAccountRequest;
import com.takehome.eagle.repository.AccountRepository;
import com.takehome.eagle.repository.UserRepository;
import com.takehome.eagle.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.time.OffsetDateTime;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private static final String PREFIX = "01";

    private static final int TOTAL_LENGTH = 8; // prefix + 6 digits
    private static final int RANDOM_DIGITS_LENGTH = TOTAL_LENGTH - PREFIX.length();
    private static final SecureRandom RANDOM = new SecureRandom();

    @Override
    public BankAccountResponse createBankAccount(CreateBankAccountRequest request) {
    log.info("Creating bank account with details: {}", request);
    var user = userRepository.getUserByName(request.getName());
    if (user.isPresent()) {
        log.info("Creating acct for user: {}", user.get());
        Accounts account = Accounts.builder()
                .user(user.get())
                .accountId(UUID.randomUUID())
                .name(request.getName())
                .accountType(BankAccountResponse.AccountTypeEnum.PERSONAL) //defaulting as only one enum present
                .createdTimestamp(OffsetDateTime.now())
                .updatedTimestamp(OffsetDateTime.now())
                .sortCode(Accounts.SortCodeEnum._10_10_10)
                .accountNumber(generateAccountNumber())
                .balance(BigDecimal.ZERO)
                .currency(Accounts.CurrencyEnum.GBP)
                .build();
        Accounts persistedAccount = accountRepository.save(account);
        return mapToBankAccountResponse(persistedAccount);
    } else {
        log.error("User not found with name: {}", request.getName());
        throw new RuntimeException("User not found");
    }
    }

    private BankAccountResponse mapToBankAccountResponse(Accounts account) {
        BankAccountResponse response = new BankAccountResponse();
        response.accountNumber(account.getAccountId().toString());
        response.name(account.getName());
        response.accountType(account.getAccountType());
        response.createdTimestamp(account.getCreatedTimestamp());
        response.updatedTimestamp(account.getUpdatedTimestamp());
        response.currency(BankAccountResponse.CurrencyEnum.GBP);
        response.balance(account.getBalance().doubleValue());
        return response;
    }

    private static String generateAccountNumber() {
        StringBuilder sb = new StringBuilder(PREFIX);
        for (int i = 0; i < RANDOM_DIGITS_LENGTH; i++) {
            int digit = RANDOM.nextInt(10);  // 0-9
            sb.append(digit);
        }
        return sb.toString();
    }
}
