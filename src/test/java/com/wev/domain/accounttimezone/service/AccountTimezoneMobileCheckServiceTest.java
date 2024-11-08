package com.wev.domain.accounttimezone.service;

import com.wev.domain.account.timezone.entity.AccountTimezoneEntity;
import com.wev.domain.account.timezone.TimezoneRepository;
import com.wev.domain.account.timezone.TimezoneService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.Optional;
import java.time.ZonedDateTime;

// Service [input] => [output]
// Service ([TimezoneRepository])

@ExtendWith(MockitoExtension.class)
class AccountTimezoneMobileCheckServiceTest {

    @Mock
    private TimezoneRepository accountTimezoneRepository;

    // 저장된 게정이 없다.

    @InjectMocks
    private TimezoneService accountTimezoneService;

    // 사용자의 time-zone 업데이트 API
    // Testcase
    // 1. 타임존이 올바르지 않으면, 에러 발생(InvalidTimezoneException)
    // 2. 계정이 잘못됬으면(없으면) 에러 발생(NotFoundAccountTimezoneException)
    // 3. 계정이 멀쩡하면 업데이트된다
    @Nested
    class updateAccountTimezone {
        Long accountId = 111L;
        String timezone = "Europe/Berlin";

        @Test
        void number2() {
            // mocking
            accountTimezoneRepository.findByAccountId => null return

            try {
                accountTimezoneService.updateAccountTimezone(accountId, timezone);

                accountTimezoneRepository.findByAccountId => param = accountId
            } catch (e) {
                e === NotFoundAccountTimezoneException
            }
        }

        @Test
        void number3() {
            accountTimezoneRepository.findByAccountId => AccountTimezone()  return

            result = accountTimezoneService.updateAccountTimezone(accountId, timezone);

            accountTimezoneRepository.findByAccountId => param = accountId
            accountTimezoneRepository.update => param = UpdateAccount
            result = {aaaa}
        }

    }
}

