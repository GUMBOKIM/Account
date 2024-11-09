package com.wev.account.domain.account.timezone;

import com.wev.account.domain.account.Account;
import com.wev.account.domain.account.timezone.model.AccountTimezoneDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountId(Long accountId);
}
