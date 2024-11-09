package com.wev.account.domain.account.timezone;

import com.wev.account.domain.account.Account;
import com.wev.account.domain.account.timezone.model.AccountTimezoneMapper;
import com.wev.account.domain.account.timezone.exception.AccountNotFoundException;
import com.wev.account.domain.account.timezone.exception.TimezoneUpdateDeferredException;
import com.wev.account.controller.AccountTimezoneWebDTO.*;
import com.wev.account.domain.account.timezone.model.AccountTimezoneServiceDTO.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

// 프맂젠테이션레이어
// <=>
// 어플리케이션 레이어
// <=>
// 도메인 레이어

// Account => AccountTimezone

@Service
@RequiredArgsConstructor
public class AccountTimezoneService {
    // TODO : private로 변경
    private final int MAX_OFFSET_DIFF_SECONDS = 60 * 60 * 2;

    private final AccountRepository accountRepository;

    // TODO : DTO 리턴은 도메인 레이어 => 어플리케이션 레이어 할때만 변환해서 내주기!!!!!
    // 1) timezone 업데이트 여부 확인
    public boolean isTimezoneUpdateRequired(Long accountId, String currentTimezone) {
        Account account = findAccountOrExceptionThrow(accountId);

        if (isTimezoneNull(account)) {
            return true;
        }

        return isOffsetDifferenceAboveThreshold(account.getTimezone(), currentTimezone);
    }

    // Testcase
    // 1. 계정이 없을때 => throw error(특정에러)
    // 2. 타임존 컬럼이 null => return True
    // 3. 기존 타임존이랑 비교해서 2시간 이상이면 => return True
    // 4. DB타임존이랑 비교해서 2시간 미만이면 => return False
//
//    // 2) timezone or deferred 업데이트
//    @Transactional
//    public GetWebRes updateTimezoneAndDeferred(
//            Long accountId,
//            String timezone,
//            boolean isDeferred
//    ) {
//        Account account = findAccountOrExceptionThrow(accountId);
//
//        if (isUpdateDeferred(account)) {
//            throw new TimezoneUpdateDeferredException(account.getUpdateDeferredUntil());
//        }
//
//        return ;
//    }
//
//    // 3) 모든 timezone 조회 (zoneId 사용 - IANA 표준 타임존 데이터베이스)
//    public List<String> getAllTimezones() {
//        return ZoneId.getAvailableZoneIds()
//                .stream()
//                .sorted()
//                .collect(Collectors.toList());
//    }

    private Account findAccountOrExceptionThrow(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(accountId));
    }


    private boolean isTimezoneNull(Account account) {
        return account.getTimezone() == null;
    }

    private boolean isOffsetDifferenceAboveThreshold(String savedTimezone, String currentTimezone) {
        ZoneId dbZoneId = ZoneId.of(savedTimezone);
        ZoneId currentZoneId = ZoneId.of(currentTimezone);

        ZonedDateTime dbZoneTime = ZonedDateTime.now(dbZoneId);
        ZonedDateTime currentZoneTime = ZonedDateTime.now(currentZoneId);

        int offsetDifference = Math.abs(dbZoneTime.getOffset().getTotalSeconds() - currentZoneTime.getOffset().getTotalSeconds());
        return offsetDifference >= MAX_OFFSET_DIFF_SECONDS;
    }
//
//    private boolean isUpdateDeferred(Account account) {
//        return account.getUpdateDeferredUntil() != null
//                && account.getUpdateDeferredUntil().isAfter(ZonedDateTime.now());
//    }
}