package com.wev.account.domain.account.timezone.model;

import com.wev.account.domain.account.Account;
import com.wev.account.controller.AccountTimezoneWebDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AccountTimezoneMapper {
    AccountTimezoneMapper INSTANCE = Mappers.getMapper(AccountTimezoneMapper.class);

    AccountTimezoneWebDTO.GetWebRes toWebDto(Account account);

    AccountTimezoneServiceDTO.UpdateDTO toServiceDto(AccountTimezoneWebDTO.UpdateWebReq updateWebReq, Long accountId);
}