package com.wev.domain.account.timezone.entity;

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
@Table(name = "ACCOUNT_TIMEZONE")
public class AccountTimezoneEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    // TODO: String 쓰는거 지양하셈
    @Column()
    private String timezone;

    @Column()
    // 타임존 업데이트 유보 시간
    private ZonedDateTime updateDeferredUntil;
}