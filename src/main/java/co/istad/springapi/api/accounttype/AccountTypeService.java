package co.istad.springapi.api.accounttype;

import co.istad.springapi.api.accounttype.web.AccountTypeDto;
import co.istad.springapi.api.accounttype.web.CreateAccountTypeDto;
import co.istad.springapi.api.user.web.UserDto;

import java.util.List;

public interface AccountTypeService {
    List<AccountTypeDto> findAll();

    AccountTypeDto createAccountType(CreateAccountTypeDto createAccountTypeDto);
    AccountTypeDto findAccountTypeById(Integer id);
    Integer deleteAccountType(Integer id);
    AccountTypeDto update(Integer id, CreateAccountTypeDto createAccountTypeDto);
}
