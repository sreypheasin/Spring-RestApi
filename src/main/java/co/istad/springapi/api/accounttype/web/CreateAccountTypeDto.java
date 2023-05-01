package co.istad.springapi.api.accounttype.web;

import jakarta.validation.constraints.NotBlank;

public record CreateAccountTypeDto(@NotBlank(message = "Please Enter you name...") String name) {
}
