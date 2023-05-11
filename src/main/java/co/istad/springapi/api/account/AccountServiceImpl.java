package co.istad.springapi.api.account;

import co.istad.springapi.api.account.Account;
import co.istad.springapi.api.account.AccountMapStruct;
import co.istad.springapi.api.account.AccountMapper;
import co.istad.springapi.api.account.AccountServices;
import co.istad.springapi.api.account.web.AccountDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountServices {
    private final AccountMapper accountMapper;
    private final AccountMapStruct accountMapStruct;

    @Override
    public List<AccountDto> findAll() {
        List<Account> accounts = accountMapper.select();
        for(Account account: accounts){
            log.info("accountName = {}",account.getAccountName());
        }
        return accountMapStruct.accountDtoToDtoList(accounts);
    }
}
