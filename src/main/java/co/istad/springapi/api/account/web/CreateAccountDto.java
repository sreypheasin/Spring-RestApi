package co.istad.springapi.api.account.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateAccountDto(
        @NotBlank(message = "Account number cannot blank") String accountNO,
        String profile,
        @NotNull(message = "Pin is required...") Integer pin,
        @NotBlank(message = "Password is required...") String password,

        @NotBlank(message = "Phone number is required...") Integer phoneNumber,
        @NotBlank(message = "Account type is required...") Integer accountType
) {
}
