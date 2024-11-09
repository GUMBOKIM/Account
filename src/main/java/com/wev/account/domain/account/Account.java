package com.wev.account.domain.account;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ACCOUNT_TIMEZONE")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    // 타임존 ID (America/New_York, Asia/Seoul, Asia/Tokyo ...)
    private String timezone;

    // 타임존 업데이트 유보 시간
    @Column(nullable = true)
    private ZonedDateTime updateDeferredUntil;

    // TODO: constructor를 private로 만들고
    public static Account create(
            Long accountId,
            String timezone,
            ZonedDateTime updateDeferredUntil
    ) {
        // TODO: timezone 체크로직
        return new Account(accountId, timezone, updateDeferredUntil);
    }

    public static Account create(
            Long accountId,
            ZonedDateTime updateDeferredUntil
    ) {
        Account account = new Account();
        account.accountId = accountId;
        account.updateDeferredUntil = updateDeferredUntil;
        return account;
    }

    public void updateTimezone(String timezone) {
        this.timezone = timezone;
    }

    public void setUpdateDeferredUntil1Month() {
        this.updateDeferredUntil = ZonedDateTime.now().plusMonths(1); // 1개월 유보 시간 설정
    }
}