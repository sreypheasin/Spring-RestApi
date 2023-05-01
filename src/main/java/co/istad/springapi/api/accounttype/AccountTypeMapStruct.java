package co.istad.springapi.api.accounttype;

import co.istad.springapi.api.accounttype.web.AccountTypeDto;
import co.istad.springapi.api.accounttype.web.CreateAccountTypeDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountTypeMapStruct {
    List<AccountTypeDto> toDtoList(List<AccountType> model);
    AccountTypeDto accountTypeToAccountTypeDto(AccountType accountType);
    AccountType createAccountTypeDtoToAccountType(CreateAccountTypeDto createAccountTypeDto);
}
