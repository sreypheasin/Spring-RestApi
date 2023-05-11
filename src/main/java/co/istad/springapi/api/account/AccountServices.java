package co.istad.springapi.api.account;

import co.istad.springapi.api.account.web.AccountDto;

import java.util.List;

public interface AccountServices {
    //find all
    List<AccountDto> findAll();
}
