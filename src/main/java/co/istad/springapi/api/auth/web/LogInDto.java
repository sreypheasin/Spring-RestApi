package co.istad.springapi.api.auth.web;

import co.istad.springapi.api.user.validator.password.Password;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LogInDto(@NotBlank
                       @Email
                       String email,
                       @NotBlank
                       @Password
                       String password) {

}
