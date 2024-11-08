package com.wev.domain.accounttimezone.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountTimezone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    // 타임존 ID (America/New_York, Asia/Seoul, Asia/Tokyo ...)
    private String timezone;

    // 타임존 업데이트 유보 시간
    private ZonedDateTime updateDeferredUntil;

}