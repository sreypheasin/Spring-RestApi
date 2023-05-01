package co.istad.springapi.api.accounttype.web;

import co.istad.springapi.api.accounttype.AccountTypeService;
import co.istad.springapi.api.base.BaseRest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/account-types")
@RequiredArgsConstructor
public class AccountTypeRestController {
    private  final AccountTypeService accountTypeService;
    @GetMapping
    public BaseRest<?> finaAll(){
        var accountTypeDtoList = accountTypeService.findAll();
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Account types have been found")
                .data(accountTypeDtoList)
                .build();
    }
    // TODO: find by id

    @GetMapping("/{id}")
    public BaseRest<?> findAccountTypeById(@PathVariable("id") Integer id) {
        AccountTypeDto accountTypeDto = accountTypeService.findAccountTypeById(id);
        return BaseRest.builder().status(true)
                .code(HttpStatus.OK.value())
                .message("Account type have been found")
                .timestamp(LocalDateTime.now())
                .data(accountTypeDto).build();
    }

    //TODO: Create new
    @PostMapping
    public BaseRest<?> createNewAccountType(@RequestBody @Valid CreateAccountTypeDto createAccountTypeDto){
        AccountTypeDto accountTypeDto = accountTypeService.createAccountType(createAccountTypeDto);
        return BaseRest.builder().status(true)
                .code(HttpStatus.OK.value())
                .message("Account type have been created")
                .timestamp(LocalDateTime.now())
                .data(accountTypeDto).build();
    }

    //TODO: Update
    @PutMapping("/{id}")
    public BaseRest<?> updateAccountTypeById(@PathVariable("id") Integer id, @RequestBody @Valid CreateAccountTypeDto createAccountTypeDto){
        AccountTypeDto accountTypeDto = accountTypeService.update(id, createAccountTypeDto);
        return BaseRest.builder()
                .status(true).
                code(HttpStatus.OK.value()).
                message("Account type have been updated ").
                timestamp(LocalDateTime.now())
                .data(accountTypeDto).build();
    }

    //TODO: delete
    @DeleteMapping("/{id}")
    public BaseRest<?> deleteAccountType(@PathVariable("id") Integer id) {
        Integer deletedId = accountTypeService.deleteAccountType(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Account type have been deleted")
                .timestamp(LocalDateTime.now())
                .data(deletedId).build();
    }



}
