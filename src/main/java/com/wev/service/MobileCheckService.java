package com.wev.service;

import com.wev.domain.account.timezone.TimezoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.server.ServerErrorException;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class MobileCheckService {
    private final TimezoneService accountTimezoneService;

    // 1. 사용자의 time-zone 업데이트 필요 여부 확인 API
    // TestCase

    // 2. 사용자의 time-zone 업데이트 API
    // TestCase
    public void exampleMethod() {
        try {
            this.accountTimezoneService.getTimezones();
        } catch {

            throw new ServerErrorException();

        }

    }

    // 3. time-zone 전체 목록 API
}
