package co.istad.springapi.api.account.web;

import co.istad.springapi.api.accounttype.AccountType;
import jakarta.validation.constraints.NotBlank;

public record AccountDto(String accountNo,
                         String profile,
                         String phoneNumber,
                         Double transferLimit,
                         AccountType accountType
                        ) { }
