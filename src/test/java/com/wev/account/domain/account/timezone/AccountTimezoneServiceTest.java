package com.wev.account.domain.account.timezone;

import com.wev.account.domain.account.Account;
import com.wev.account.domain.account.timezone.exception.AccountNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import javax.swing.text.html.Option;
import java.time.ZonedDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AccountTimezoneServiceTest {
    private AccountTimezoneService accountTimezoneService;

    @Mock
    private AccountRepository accountRepository;



    @BeforeEach
    void setUp() {
        accountTimezoneService = new AccountTimezoneService(accountRepository);
    }


    @Nested
    @DisplayName("isTimezoneUpdateRequired")
    class isTimezoneUpdateRequired {
        Long accountId = 999L;
        String currentTimezone = "Asia/Seoul";
        ZonedDateTime updateDeferredUntil = ZonedDateTime.parse("2020-01-01T00:00:00Z");

        @Test
        @DisplayName("계정이 없을때 => throw error")
        void NotExistAccount() {
            accountRepository = mock(AccountRepository.class);
            when(accountRepository.findByAccountId(accountId)).thenReturn(null);

            assertThrows(AccountNotFoundException.class, () -> {
                accountTimezoneService.isTimezoneUpdateRequired(accountId, currentTimezone);
            });
        }

        @Test
        @DisplayName("타임존 컬럼이 null => return True")
        void AccountTimezoneFieldNull() {
            Account account = Account.create(
                    accountId,
                    updateDeferredUntil
            );
            accountRepository = mock(AccountRepository.class);
            when(accountRepository.findByAccountId(accountId)).thenReturn(
                    Optional.of(account)
            );

            boolean result = accountTimezoneService.isTimezoneUpdateRequired(accountId, currentTimezone);

            assertTrue(result);
        }

        @Test
        @DisplayName("기존 타임존이랑 비교해서 2시간 이상이면 => return True")
        void TimezoneBiggerThanEqual2Hour() {
            Account account = Account.create(
                    accountId,
                    updateDeferredUntil
            );
            accountRepository = mock(AccountRepository.class);
            when(accountRepository.findByAccountId(accountId)).thenReturn(
                    Optional.of(account)
            );

            boolean result = accountTimezoneService.isTimezoneUpdateRequired(accountId,
                    "Timezone"
            );

            assertTrue(result);
        }


        @Test
        @DisplayName("기존 타임존이랑 비교해서 2시간 이하이면 => return False")
        void TimezoneLessThan2Hour() {
            Account account = Account.create(
                    accountId,
                    updateDeferredUntil
            );
            accountRepository = mock(AccountRepository.class);
            when(accountRepository.findByAccountId(accountId)).thenReturn(
                    Optional.of(account)
            );

            boolean result = accountTimezoneService.isTimezoneUpdateRequired(accountId,                     "Timezone");

            assertFalse(result);
        }
    }

}