package com.wev.domain.account.timezone;

import com.wev.domain.account.timezone.entity.AccountTimezoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TimezoneRepository extends JpaRepository<AccountTimezoneEntity, Long> {
    Optional<AccountTimezoneEntity> findByAccountId(Long accountId);
}
