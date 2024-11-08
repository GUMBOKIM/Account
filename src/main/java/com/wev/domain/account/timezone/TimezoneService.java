package com.wev.domain.account.timezone;

import com.wev.domain.account.timezone.entity.AccountTimezoneEntity;
import com.wev.domain.account.timezone.exception.InvalidTimezoneException;
import com.wev.domain.account.timezone.exception.NotFoundAccountTimezoneException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class TimezoneService {
    // TODO : 알아서 쓰셈
    private final int DURATION_TIME = 12 * 60 * 1000;
    private final TimezoneRepository accountTimezoneRepository;

    // 사용자의 time-zone 업데이트 필요 여부 확인 API
    // 현재 시간을 받아야함
    public boolean checkAccountTimezoneSsibal(Long accountId, Date currentTime) {
        // find logic
        if(true) {
            throw new NotFoundAccountTimezoneException();
        }

        // caclualte

        return false;
    }

    // Testcase
    // 1. 만약에 계정이 없어요. 에러가 나요 (NotFoundAccountTimezoneException)
    // 2. 어떤새끼가 DURATION_TIME < 현재시간 - 마지막 기록 시간 => False
    // 3. 어떤새끼가 DURATION_TIME > 현재시간 - 마지막 기록 시간 => True

    // 사용자의 time-zone 업데이트 API
    public AccountTimezoneEntity updateAccountTimezone(
            Long accountId, String timezone
    ) throws InvalidTimezoneException, NotFoundAccountTimezoneException {
        // validation
        if(this.isValidTimezone(timezone)) {
            throw new InvalidTimezoneException();
        }

        // find logic
        if(true) {
            throw new NotFoundAccountTimezoneException();
        }

        // update logic

        return new AccountTimezoneEntity();
    }
    // Testcase
    // 1. 타임존이 올바르지 않으면, 에러 발생(InvalidTimezoneException)
    // 2. 계정이 잘못됬으면(없으면) 에러 발생(NotFoundAccountTimezoneException)
    // 3. 계정이 멀쩡하면 업데이트된다

    // time-zone 전체 목록 API
    public String[] getTimezones() {
        return {""};
    }

    // Testcase
    // 1. 타임존 전체목록을 반환한다

//    private boolean isValidTimezone(String timezone) {
//        // 로직
//        return false;
//    }
//
//    public boolean isTimezoneUpdateRequired(Long accountId, String currentTimezone) {
//        Optional<AccountTimezoneEntity> accountOpt = accountTimezoneRepository.findByAccountId(accountId);
//
//        if (accountOpt.isPresent()) {
//            AccountTimezoneEntity accountTimezoneEntity = accountOpt.get();
//
//            // 타임존이 없는 경우 업데이트 필요
//            if (isTimezoneNull(accountTimezoneEntity)) {
//                return true;
//            }
//
//            // 오프셋 차이가 2시간 이상인지 확인
//            return isOffsetDifferenceAboveThreshold(accountTimezoneEntity.getTimezone(), currentTimezone);
//        }
//
//        // 계정을 찾지 못한 경우 업데이트가 필요하지 않다고 가정
//        return false;
//    }
//
//    private boolean isTimezoneNull(AccountTimezoneEntity accountTimezoneEntity) {
//        return accountTimezoneEntity.getTimezone() == null;
//    }
//
//    private boolean isOffsetDifferenceAboveThreshold(String dbTimezone, String currentTimezone) {
//        ZoneId dbZoneId = ZoneId.of(dbTimezone);
//        ZoneId currentZoneId = ZoneId.of(currentTimezone);
//
//        ZonedDateTime dbZoneTime = ZonedDateTime.now(dbZoneId);
//        ZonedDateTime currentZoneTime = ZonedDateTime.now(currentZoneId);
//
//        int offsetDifference = Math.abs(dbZoneTime.getOffset().getTotalSeconds() - currentZoneTime.getOffset().getTotalSeconds());
//        return offsetDifference >= 7200;
//    }
}
