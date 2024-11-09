package com.wev.account.domain.account.timezone.model;

import java.time.ZonedDateTime;

public interface AccountTimezoneDTO {
    Long getAccountId();
    String getTimezone();
    ZonedDateTime getUpdateDeferredUntil();
}