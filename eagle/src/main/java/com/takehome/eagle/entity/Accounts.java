package com.takehome.eagle.entity;

import com.takehome.eagle.model.BankAccountResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "ACCOUNTS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Accounts {

    @Id
    @GeneratedValue
    @Column(name = "account_id", columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID accountId;

    @Column(name = "account_number", nullable = false)
    private String accountNumber;

    public enum SortCodeEnum {
        _10_10_10("10-10-10");

        private final String value;

        SortCodeEnum(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "sort_code", nullable = false, length = 20)
    private SortCodeEnum sortCode;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type", nullable = false, length = 50)
    private BankAccountResponse.AccountTypeEnum accountType;

    @Column(name = "balance", nullable = false, precision = 15, scale = 2)
    private BigDecimal balance;

    public enum CurrencyEnum {
        GBP("GBP");

        private final String value;

        CurrencyEnum(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "currency", nullable = false, length = 10)
    private CurrencyEnum currency;

    @Column(name = "created_timestamp", nullable = false)
    private OffsetDateTime createdTimestamp;

    @Column(name = "updated_timestamp", nullable = false)
    private OffsetDateTime updatedTimestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
    private User user;
}
