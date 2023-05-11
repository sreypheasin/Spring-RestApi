package co.istad.springapi.api.account.web;

import co.istad.springapi.api.account.AccountServices;
import co.istad.springapi.api.base.BaseRest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountRestController {
    private final AccountServices accountServices;
    @GetMapping
    public BaseRest<?> findAll(){
        var accountDtoList= accountServices.findAll();
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Account have been found")
                .timestamp(LocalDateTime.now())
                .data(accountDtoList)
                .build();
    }

}
