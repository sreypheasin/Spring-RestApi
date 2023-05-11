package co.istad.springapi.api.account;

import co.istad.springapi.api.account.Account;
import co.istad.springapi.api.account.web.AccountDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapStruct {
    List<AccountDto>  accountDtoToDtoList (List<Account> model);
}
