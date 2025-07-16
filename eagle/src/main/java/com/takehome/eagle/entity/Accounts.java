package com.takehome.eagle.entity;

import com.takehome.eagle.model.BankAccountResponse;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "bank_accounts")
public class Accounts {

    @Id
    @GeneratedValue
    @Column(name = "account_id", columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID accountId;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type", nullable = false)
    private BankAccountResponse.AccountTypeEnum accountType;

    @Column(name = "user_id", nullable = false, columnDefinition = "uuid")
    private UUID userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
    private User user;


}
